package com.pixy.codebase.extensions

import android.view.View
import android.view.View.OnClickListener
import androidx.fragment.app.Fragment
import com.pixy.codebase.R
import com.pixy.codebase.providers.ViewProvider
import com.pixy.codebase.utils.Color
import com.pixy.codebase.utils.OneArgCallback
import com.pixy.codebase.utils.dpToPx

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

inline fun <reified X> View.getData(key: Int): X {
    return getTag(key) as X
}

fun<X> View.setData(key: Int, value: X) {
    setTag(key, value)
}

internal var View.lastClickTime: Long
get() {
   return getData(R.id.click_time)
}
set(value) {
    setData(R.id.click_time , value)
}

internal val View.freeToClick: Boolean get() {
    return System.currentTimeMillis() >= lastClickTime + 500
}

internal fun View.staticClick(clickListener: OnClickListener) {
    setOnClickListener {
        if(freeToClick && isEnabled && isClickable)
            clickListener.onClick(this)
    }
}

fun View.getColor(color: Color?): Int {
    return color?.let {
        com.pixy.codebase.utils.getColor(context, color)
    }?: kotlin.run {
        android.graphics.Color.TRANSPARENT
    }

}


fun View.setCClickListener(callback: OneArgCallback<View>) {
        staticClick(callback)
}

/**
 * cash view true must be use with SingleObserver
 */
fun <T: View> Fragment.views(useCash: Boolean = false, initialize: () -> T): ViewProvider<T> {
    return ViewProvider(useCash, initialize).also { it.lifecycleAware(this.lifecycle) }
}