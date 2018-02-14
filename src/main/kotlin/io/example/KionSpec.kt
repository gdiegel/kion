package io.example

@KionTest
open class KionSpec {
    fun <T> given(description: String, body: () -> T) {
        println("Spec $description")
        try {
            body()
        } finally {
            println("Exiting given block")
        }
    }
}