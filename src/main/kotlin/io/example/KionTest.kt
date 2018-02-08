package io.example

import org.junit.platform.commons.annotation.Testable

@kotlin.annotation.Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@kotlin.annotation.MustBeDocumented
@Testable
annotation class KionTest