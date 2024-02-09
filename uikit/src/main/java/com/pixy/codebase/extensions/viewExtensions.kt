package com.pixy.codebase.extensions

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
import com.pixy.codebase.R
import com.pixy.codebase.providers.ViewProvider
import com.pixy.codebase.utils.Color
import com.pixy.codebase.utils.SimpleCallback
import com.pixy.codebase.utils.dpToPx
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by emadmahouti on 5/12/23
 */


fun View.shape(backgroundColor: Int? = null, borderColor: Int? = null, borderSize: Int = 1.dpToPx, corners: Int? = null) {
    var materialShape = com.pixy.codebase.utils.MyMaterialShape()
    if(backgroundColor != null)
        materialShape = materialShape.color(backgroundColor)

    if(borderColor != null)
        materialShape = materialShape.border(borderColor, borderSize)

    if(corners != null)
        materialShape = materialShape.corners(corners)

    background = materialShape
}

fun View.hide() {
    visibility = View.GONE
}


fun View.show() {
    visibility = View.VISIBLE
}

private fun View.withinClickTime(): Boolean //checks if the interval between touch down and touch up is short enough
{
    return System.currentTimeMillis() - getLastClickTime() < 500
}

private fun View.freeToClick(): Boolean {
    return System.currentTimeMillis() - getLastClickTime() > 1000
}

private fun View.getLastClickTime(): Long {
    var lastClickTime: Long = 0
    val `object` = getTag(R.id.view_last_click_time)
    if (`object` != null) {
        lastClickTime = `object` as Long
    }
    return lastClickTime
}

private fun View.setLastClickTimeNow() {
    setTag(R.id.view_last_click_time, System.currentTimeMillis())
}

fun View.setCClickListener(animated: Boolean = true, simpleCallback: SimpleCallback) {
    setCClickListener(animated, View.OnClickListener { simpleCallback.invoke() })
}

@SuppressLint("ClickableViewAccessibility")
fun View.setCClickListener(animated: Boolean = true, clickListener: View.OnClickListener?) {
    clickListener?.let {
        if (animated) {
            setAnimativeClickListener(it)
        } else {
            setStaticClickListener(it)
        }
    } ?: kotlin.run {
        setOnClickListener(null)
        return
    }
}

@SuppressLint("ClickableViewAccessibility")
private fun View.setAnimativeClickListener(clickListener: View.OnClickListener) {
    val touchDownCaptured = AtomicBoolean(false)
    setOnTouchListener { _: View?, event: MotionEvent ->
        if (isEnabled) {
            val action = event.action
            if (action == MotionEvent.ACTION_DOWN) {
                if (freeToClick()) {
                    touchDownCaptured.set(true)
                    setLastClickTimeNow()
                    animateToClickedState()
                }
                return@setOnTouchListener true
            } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                if (touchDownCaptured.get()) {
                    touchDownCaptured.set(false)
                    animateToDefaultState(action, clickListener)
                }
                return@setOnTouchListener true
            }
        }
        return@setOnTouchListener false
    }

}

private fun View.animateToClickedState() {
    animate().scaleX(.98f).scaleY(.98f).setDuration(50).start()
}

private fun View.animateToDefaultState(action: Int, clickListener: View.OnClickListener) {
    animate().scaleX(1f).scaleY(1f).setDuration(50)
        .withEndAction {
            if (withinClickTime() && action == MotionEvent.ACTION_UP)
                postDelayed(
                    {
                        clickListener.onClick(this)
                    }, 100
                )
        }.start()
}


private fun View.setStaticClickListener(clickListener: View.OnClickListener) {
    setOnClickListener {
        if (isEnabled && freeToClick()) {
            setLastClickTimeNow()
            clickListener.onClick(this)
        }
    }
}

fun View.getColor(color: Color?): Int {
    return color?.let {
        com.pixy.codebase.utils.getColor(context, color)
    }?: kotlin.run {
        android.graphics.Color.TRANSPARENT
    }

}

/**
 * cash view true must be use with SingleObserver
 */
fun <T: View> Fragment.views(useCash: Boolean = false, initialize: () -> T): ViewProvider<T> {
    return ViewProvider(useCash, initialize).also { it.lifecycleAware(this.lifecycle) }
}