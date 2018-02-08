package io.example

import org.junit.platform.engine.TestDescriptor
import org.junit.platform.engine.UniqueId
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor

class KionUnit(counter: Int, step: Step) : AbstractTestDescriptor(
        UniqueId.root("unit", "#$counter"), step.name
) {
    override fun getType(): TestDescriptor.Type {
        return TestDescriptor.Type.TEST
    }

    override fun toString(): String {
        return "KionUnit($displayName)"
    }


}
