package uz.gita.dictionary_slp.utils

import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup

fun View.closeAnimation() {
    this.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    val height = this.measuredHeight
    val anim = ValueAnimator.ofInt(height - 1.px, 1.px)
    anim.addUpdateListener { valueAnimator ->
        val value = valueAnimator.animatedValue as Int
        val layoutParams: ViewGroup.LayoutParams = this.layoutParams
        layoutParams.height = value
        this.alpha = (value ).toFloat() / (height)
        this.layoutParams = layoutParams
    }
    anim.duration = 500
    anim.start()
}

fun View.openAnimation() {
    this.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    val height = this.measuredHeight
    this.visibility = View.VISIBLE
    val anim = ValueAnimator.ofInt(1.px, height - 1.px)
    anim.addUpdateListener { valueAnimator ->
        val value = valueAnimator.animatedValue as Int
        val layoutParams: ViewGroup.LayoutParams = this.layoutParams
        layoutParams.height = value
        this.alpha = (value).toFloat() / (height)
        this.layoutParams = layoutParams
    }
    anim.duration = 500
    anim.start()
}