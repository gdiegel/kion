package io.github.gdiegel.kion

import org.assertj.core.api.Assertions.assertThat

class MySpec : KionSpec() {

    @Kion
    fun will_pass() {
        "A should be equal to a" spec {
            assertThat("a").isEqualTo("a")
        }
    }

    @Kion
    fun will_fail() {
        "A should be equal to a" spec {
            assertThat("a").isEqualTo("b")
        }
    }
}
