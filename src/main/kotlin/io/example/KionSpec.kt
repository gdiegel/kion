package io.example

open class KionSpec {

    infix fun String.spec(body: () -> Unit) {
        println("Spec $this")
        try {
            body()
        } finally {
            println("Exiting spec block")
        }
    }

}