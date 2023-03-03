package com.coderipper.hsma.utils

import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable

fun startAnimatedIcon(drawable: Drawable) {
    val animatedIcon = drawable as AnimatedVectorDrawable
    animatedIcon.start()
}