package io.github.gdiegel.kion

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