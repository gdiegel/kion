package io.example

import org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder
import org.junit.platform.launcher.core.LauncherFactory
import org.junit.platform.launcher.listeners.SummaryGeneratingListener
import java.io.PrintWriter

fun main(args: Array<String>) {
    val request = LauncherDiscoveryRequestBuilder.request()
            .selectors(
                    selectPackage("io.example")
            )
            .build()

    val launcher = LauncherFactory.create()
    val summaryGeneratingListener = SummaryGeneratingListener()
    launcher.registerTestExecutionListeners(summaryGeneratingListener)
    launcher.execute(request)
    summaryGeneratingListener.summary.printTo(PrintWriter(System.out))

}