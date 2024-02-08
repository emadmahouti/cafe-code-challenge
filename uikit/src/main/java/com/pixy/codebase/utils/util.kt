package com.pixy.codebase.utils

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

typealias SimpleCallback = () -> Unit
typealias OneArgCallback<T> = (T) -> Unit
typealias TwoArgsCallback<T, I> = (T, I) -> Unit
typealias ThreeArgsCallback<T, I, J> = (T, I, J) -> Unit