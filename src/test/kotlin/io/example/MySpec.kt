package io.example

import org.assertj.core.api.Assertions.assertThat

class MySpec : KionSpec() {

    @Kion
    fun will_pass() {
        "A should be equal to a" spec {
            assertThat("a").isEqualTo("a")
        }
    }
}
