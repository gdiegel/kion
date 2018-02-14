package io.example

import org.assertj.core.api.Assertions.assertThat


class FirstTestClass : KionSpec() {

    @Spec
    fun uno() {
        given("Estoy tomando chicha", { assertThat("a").isEqualTo("a") })
    }

    @Spec
    fun dos() {
        given("Estoy tomando una más", { assertThat("a").isEqualTo("b") })
    }
}