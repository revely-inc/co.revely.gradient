RevelyGradient
========
[ ![Download](https://api.bintray.com/packages/revely/maven/RevelyGradient/images/download.svg) ](https://bintray.com/revely/maven/RevelyGradient/_latestVersion)

RevelyGradient is an Android library for easy gradient management !

About Revely
========

### [<img src="https://www.revely.co/images/logo.svg" width="40">](https://www.revely.co/) [Web site](https://www.revely.co/) - [<img src="https://forum.gitlab.com/uploads/default/original/1X/513cb6bf2c12e334f0cb4864dbc585587e712af4.png" width="30">](https://gitlab.com/revely) [Open Source projects](https://gitlab.com/revely) - [<img src="https://c5.patreon.com/external/logo/downloads_logomark_color_on_white.png" width="40">](https://www.patreon.com/revely_inc) [Become my Patreon](https://www.patreon.com/revely_inc) - [<img src="https://upload.wikimedia.org/wikipedia/fr/c/c8/Twitter_Bird.svg" width="30">](https://twitter.com/revely_inc) [Twitter](https://twitter.com/revely_inc) - [<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Instagram_logo_2016.svg/langfr-240px-Instagram_logo_2016.svg.png" width="30">](https://www.instagram.com/revely_inc/) [Instagram](https://www.instagram.com/revely_inc/) - [<img src="https://cdn.worldvectorlogo.com/logos/behance-1.svg" width="30">](https://www.behance.net/revely_inc) [Behance](https://www.behance.net/revely_inc)


Installation
========

Add the dependency
```groovy
dependencies {
    compile 'co.revely:gradient:1.0.0'
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
![demo_2](https://gitlab.com/revely/assets/raw/master/revely_gradient/text_gradient.png)

Choose the type of your gradient
```java
.radial()
.linear()
.sweep()
```

Choose the gradient colors
```java
.colors(intArrayOf(Color.parseColor("#FF2525"), Color.parseColor("#6078EA"), Color.parseColor("#6078EA")))
```

Center your gradient
```java
.center(100f, 200f)
```

Rotate the gradient around the center
```java
.angle(42)
```

Change the transparency of your gradient
```java
.alpha(0.5f)
```

Scale the gradient
```java
.scale(0.5f, 1f)
```

Change the positions of color in the gradient
```java
.offsets(floatArrayOf(0f, 0.1f, 0.5f, 1f))
```

Apply the gradient on the background of view
```java
.onBackgroundOf(text_view)
```
or directly on the view (TextView, ImageView, Button, ...)
```java
.on(text_view)
```

You can also use the layer function to stack several gradients
```java
.layer(
    RevelyGradient
        .radial(TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 150f, Resources.getSystem().displayMetrics ))
        .colors(intArrayOf(Color.parseColor("#ffdd55"), Color.parseColor("#ffdd55"), Color.parseColor("#ff543e"), Color.parseColor("#c837ab")))
        .offsets(floatArrayOf(0f, 0.1f, 0.5f, 1f))
        .center(50, 400),
    RevelyGradient
        .radial(TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 170f, Resources.getSystem().displayMetrics ))
        .colors(intArrayOf(Color.parseColor("#3771c8"), Color.parseColor("#3771c8"), Color.parseColor("#006600ff")))
        .offsets(floatArrayOf(0f, 0.128f, 1f))
        .angle(-15f)
        .scale(1f, 0.4f)
        .center(0, 0)
).onBackgroundOf(view)
```

To animate your gradient use `.animate()`
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