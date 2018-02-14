package io.example

import org.junit.platform.commons.support.ReflectionSupport.findAllClassesInPackage
import org.junit.platform.commons.util.ReflectionUtils
import org.junit.platform.engine.*
import org.junit.platform.engine.TestExecutionResult.failed
import org.junit.platform.engine.TestExecutionResult.successful
import org.junit.platform.engine.discovery.PackageSelector
import org.junit.platform.engine.support.descriptor.EngineDescriptor
import org.junit.platform.engine.support.filter.ClasspathScanningSupport.buildClassNamePredicate
import java.util.function.Predicate


/**
 * Simple test engine implementation.
 */
class KionEngine : TestEngine {

    private val isKionContainer = Predicate<Class<*>> { it.superclass == KionSpec::class.java }

    override fun getId(): String {
        return "kion-engine"
    }

    override fun discover(discoveryRequest: EngineDiscoveryRequest, uniqueId: UniqueId): TestDescriptor {
        println("Called with discoveryRequest: $discoveryRequest, uniqueId: $uniqueId")
        val classNamePredicate = buildClassNamePredicate(discoveryRequest)
        val testDescriptor = EngineDescriptor(uniqueId, id)

        discoveryRequest.getSelectorsByType(PackageSelector::class.java).forEach { selector ->
            findAllClassesInPackage(selector.packageName, isKionContainer, classNamePredicate).forEach { testClass ->
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
        engine.children.forEach { child -> execute(child, listener) }
        listener.executionFinished(engine, successful())
    }

    private fun execute(descriptor: TestDescriptor, listener: EngineExecutionListener) {
        println("Starting container ${descriptor.displayName}")
        listener.executionStarted(descriptor)
        when (descriptor) {
            is KionClass -> descriptor.children.forEach { child -> execute(child, listener) }
            is KionUnit -> {
                println("Executing ${descriptor}")
                val result = executeKionUnit(descriptor)
                println("Container ${descriptor.displayName} returned $result")
                listener.executionFinished(descriptor, result)
            }
        }
    }


    private fun executeKionUnit(child: KionUnit): TestExecutionResult {
        val instance = ReflectionUtils.newInstance(child.klass)
        try {
            ReflectionUtils.invokeMethod(child.method, instance)
        } catch (e: Throwable) {
            return failed(e)
        }
        return successful()
    }
}
