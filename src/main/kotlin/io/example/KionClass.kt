package io.example

import org.junit.platform.engine.TestDescriptor
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor
import org.junit.platform.engine.support.descriptor.ClassSource

class KionClass(
        testClass: Class<*>,
        parent: TestDescriptor
) : AbstractTestDescriptor(
        parent.uniqueId.append("class", testClass.name),
        testClass.simpleName,
        ClassSource.from(testClass)
) {

    override fun getType(): TestDescriptor.Type = TestDescriptor.Type.CONTAINER

    override fun isTest(): Boolean = false

    fun hasTests(): Boolean = true

    override fun isContainer(): Boolean = true
}