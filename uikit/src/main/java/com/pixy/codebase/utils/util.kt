package com.pixy.codebase.utils

import android.app.UiModeManager
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

val Int.dpToPx: Int
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }

const val emptyString: String = ""

fun getColor(context: Context, color: Color): Int {
    return if (!context.isDarkModeOn()) android.graphics.Color.parseColor(color.dark) else android.graphics.Color.parseColor(color.light)
}

typealias SimpleCallback = () -> Unit
typealias OneArgCallback<T> = (T) -> Unit
typealias TwoArgsCallback<T, I> = (T, I) -> Unit
typealias ThreeArgsCallback<T, I, J> = (T, I, J) -> Unit