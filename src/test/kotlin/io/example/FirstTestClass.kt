package io.example

import org.assertj.core.api.Assertions.assertThat


class FirstTestClass : KionSpec() {

    @Spec
    fun first() {
        given("this is the first method",
                {
                    assertThat("a").isEqualTo("a")
                }
        )
    }

    @Spec
    fun second() {
        given("this is the second method",
                {
                    assertThat("a").isEqualTo("b")
                }
        )
    }
}