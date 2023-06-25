package com.ssong_develop.sampleanimation

import android.animation.Animator
import android.view.View

/** view gone with animate slide up **/
fun View.animateSlideUp(animatorListener: Animator.AnimatorListener) {
    this.animate()
        .translationY(-(this.height.toFloat()))
        .alpha(0f)
        .setListener(animatorListener)
}

/** view gone with animate slide down **/
fun View.animateSlideDown(animatorListener: Animator.AnimatorListener) {
    this.animate()
        .translationY(this.height.toFloat())
        .alpha(0f)
        .setListener(animatorListener)
}