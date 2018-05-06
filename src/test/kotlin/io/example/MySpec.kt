package io.example

import org.assertj.core.api.Assertions.assertThat


class MySpec : KionSpec() {

    @Kion
    fun will_pass() {
        spec("a should be equal to a",
                {
                    assertThat("a").isEqualTo("a")
                }
        )
    }

    @Kion
    fun will_fail() {
        spec("a should be equal to b",
                {
                    assertThat("a").isEqualTo("b")
                }
        )
    }
}