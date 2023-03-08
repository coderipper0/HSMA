package com.coderipper.hsma.utils

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce

fun View.springAnimation(property: DynamicAnimation.ViewProperty, finalPosition: Float, velocity: Float): SpringAnimation {
    return SpringAnimation(this, property, finalPosition).apply {
        spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
        setStartVelocity(velocity)
    }
}

@SuppressLint("Recycle")
fun View.objectAnimationSet(values: HashMap<String, Pair<Float, Float>>, durationTime: Long): AnimatorSet {
    val animations = arrayListOf<Animator>()
    for ((property, positions) in values) {
        animations.add(this.objectAnimation(property, positions.first, positions.second, durationTime))
    }

    return AnimatorSet().apply {
        playTogether(animations)
    }
}

fun View.objectAnimation(property: String, startPosition: Float, endPosition: Float, durationTime: Long): Animator {
    return ObjectAnimator.ofFloat(this, property, startPosition, endPosition).apply {
        duration = durationTime
    }
}