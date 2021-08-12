package io.github.gdiegel.kion

import org.junit.platform.engine.TestDescriptor
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor
import org.junit.platform.engine.support.descriptor.MethodSource
import java.lang.reflect.Method

class KionUnit(method: Method, klass: Class<*>, parent: KionClass) : AbstractTestDescriptor(
        parent.getUniqueId().append("method", method.getName()),
        method.name,
        MethodSource.from(method)
) {

    internal val method: Method
    internal val klass: Class<*>

    init {
        setParent(parent)
        this.method = method
        this.klass = klass
    }

    override fun getType(): TestDescriptor.Type {
        return TestDescriptor.Type.TEST
    }

    override fun toString(): String {
        return "KionUnit(method=$method, klass=$klass)"
    }

}
