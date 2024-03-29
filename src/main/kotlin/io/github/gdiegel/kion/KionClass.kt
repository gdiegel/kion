package io.github.gdiegel.kion

import org.junit.platform.commons.util.AnnotationUtils
import org.junit.platform.commons.util.ReflectionUtils.findMethods
import org.junit.platform.engine.TestDescriptor
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor
import org.junit.platform.engine.support.descriptor.ClassSource
import java.lang.reflect.Method
import java.util.function.Predicate

class KionClass(
        klass: Class<*>,
        parent: TestDescriptor
) : AbstractTestDescriptor(
        parent.uniqueId.append("class", klass.name),
        klass.simpleName,
        ClassSource.from(klass)
) {
    val isKionUnit = Predicate<Method> { AnnotationUtils.isAnnotated(it, Kion::class.java) }

    private var klass: Class<*>

    init {
        this.klass = klass
        setParent(parent)
        addAllChildren()
    }

    private fun addAllChildren() {
        findMethods(klass, isKionUnit).stream()
                .map { method -> KionUnit(method, klass, this) }
                .peek { println(it) }
                .forEach {
                    println("Found unit ${it.displayName}")
                    this.addChild(it)
                }
    }

    override fun getType(): TestDescriptor.Type = TestDescriptor.Type.CONTAINER

    override fun isTest(): Boolean = false

    override fun isContainer(): Boolean = true
}