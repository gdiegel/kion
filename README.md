# kion
A custom test engine in Kotlin for the JUnit5 platform

## Usage

```kotlin
@Kion
fun will_pass() {
    "A should be equal to a" spec {
        assertThat("a").isEqualTo("a")
    }
}
```