RevelyGradient
========

RevelyGradient an Android library for easy gradient management !

About Revely
========

### [<img src="https://www.revely.co/images/logo.svg" width="40">](http://www.revely.co/) [Web site](http://www.revely.co/) - [<img src="https://forum.gitlab.com/uploads/default/original/1X/513cb6bf2c12e334f0cb4864dbc585587e712af4.png" width="30">](https://gitlab.com/revely) [Open Source projects](https://gitlab.com/revely) - [<img src="https://c5.patreon.com/external/logo/downloads_logomark_color_on_white.png" width="40">](https://www.patreon.com/revely_inc) [Become my Patreon](https://www.patreon.com/revely_inc) - [<img src="https://upload.wikimedia.org/wikipedia/fr/c/c8/Twitter_Bird.svg" width="30">](https://twitter.com/revely_inc) [Twitter](https://twitter.com/revely_inc) - [<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Instagram_logo_2016.svg/langfr-240px-Instagram_logo_2016.svg.png" width="30">](https://www.instagram.com/revely_inc/) [Instagram](https://www.instagram.com/revely_inc/)


Installation
========

Add the dependency
```groovy
dependencies {
    compile 'co.revely:gradient:0.0.1'
}
```

Usage
========
Kotlin
```java
RevelyGradient
    .linear()
    .colors(intArrayOf(Color.parseColor("#FF2525"), Color.parseColor("#6078EA")))
    .onBackgroundOf(view)
```

Java
```java
RevelyGradient
    .linear()
    .colors(new int[] {Color.parseColor("#FF2525"), Color.parseColor("#6078EA")})
    .onBackgroundOf(findViewById(R.id.view));
```

![demo_1](https://gitlab.com/revely/assets/raw/master/revely_gradient/background_gradient.gif)

Kotlin
```java
RevelyGradient
    .radial() // or .linear() or .sweep()
    .angle(90f)
    .center(100f, 200f)
    .alpha(0.5f)
    .colors(intArrayOf(Color.parseColor("#FF2525"), Color.parseColor("#6078EA"), Color.parseColor("#6078EA")))
    .on(text_view) // or .onBackgroundOf(text_view)
```

Java
```java
RevelyGradient
    .radial() // or .linear() or .sweep()
    .angle(90)
    .center(100, 200)
    .alpha(0.5f)
    .colors(new int[] {Color.parseColor("#FF2525"), Color.parseColor("#6078EA"), Color.parseColor("#6078EA")})
    .on(findViewById(R.id.text_view)); // or .onBackgroundOf(findViewById(R.id.text_view))
```
![demo_2](https://gitlab.com/revely/assets/raw/master/revely_gradient/text_gradient.png)


Kotlin
```java
val color1 = Color.parseColor("#00c6ff")
val color2 = Color.parseColor("#ff72ff")

val valueAnimator = ValueAnimator.ofFloat(0f, 360f)
valueAnimator.duration = 15000
valueAnimator.repeatCount = ValueAnimator.INFINITE
valueAnimator.interpolator = LinearInterpolator()
RevelyGradient.sweep()
    .colors(intArrayOf(color1, color2, color1))
    .animate(valueAnimator, { _valueAnimator, _gradientDrawable ->
         _gradientDrawable.angle = _valueAnimator.animatedValue as Float
    })
    .onBackgroundOf(container)
valueAnimator.start()
```