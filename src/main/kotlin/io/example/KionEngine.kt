package io.example

import org.junit.platform.commons.support.ReflectionSupport.findAllClassesInPackage
import org.junit.platform.engine.*
import org.junit.platform.engine.discovery.PackageSelector
import org.junit.platform.engine.support.descriptor.EngineDescriptor
import org.junit.platform.engine.support.filter.ClasspathScanningSupport.buildClassNamePredicate
import java.util.function.Predicate

private val IS_KION_CONTAINER = Predicate<Class<*>> { it.annotations.any { it.annotationClass == KionTest::class } }

/**
 * Simple test engine implementation.
 */
class KionEngine : TestEngine {

    override fun getId(): String {
        return "kion-engine"
    }

    override fun discover(discoveryRequest: EngineDiscoveryRequest, uniqueId: UniqueId): TestDescriptor {
        println("Called with discoveryRequest: $discoveryRequest, uniqueId: $uniqueId")
        val classNamePredicate = buildClassNamePredicate(discoveryRequest)
        val testDescriptor = EngineDescriptor(uniqueId, id)

        discoveryRequest.getSelectorsByType(PackageSelector::class.java).forEach { selector ->
            findAllClassesInPackage(selector.packageName, IS_KION_CONTAINER, classNamePredicate).forEach { testClass ->
                println("Adding class ${testClass.name}")
                testDescriptor.addChild(KionClass(testClass, testDescriptor))
            }
        }

        return testDescriptor
    }

    override fun execute(request: ExecutionRequest) {
        val engine = request.rootTestDescriptor
        val listener = request.engineExecutionListener
        listener.executionStarted(engine)
        for (child in engine.children) {
            listener.executionStarted(child)
            listener.executionFinished(child, TestExecutionResult.successful())
        }
        listener.executionFinished(engine, TestExecutionResult.successful())
    }
}
