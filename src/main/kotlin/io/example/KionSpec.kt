package io.example

open class KionSpec {

    fun spec(description: String?, body: () -> Unit) {
        println("Spec $description")
        try {
            body()
        } finally {
            println("Exiting spec block")
        }
    }
}