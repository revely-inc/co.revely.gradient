RevelyGradient
========

An Android library for easy gradient management

[Open Source](https://gitlab.com/revely/co.revely.gradient)

Gradle
------
```groovy
dependencies {
    compile 'co.revely:gradient:0.0.1'
}
```

Usage
-----
Kotlin
```kotlin
RevelyGradient
    .linear()
    .colors(intArrayOf(Color.CYAN, Color.YELLOW))
    .onBackgroundOf(view)
```