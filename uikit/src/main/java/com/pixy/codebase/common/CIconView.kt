package com.pixy.codebase.common

import android.content.Context
import android.content.res.ColorStateList
import androidx.appcompat.widget.AppCompatImageView

/**
 * Created by emadmahouti on 5/12/23
 */
open class CIconView(context: Context): CImageView(context) {
    fun set(res: Int, color: Int) {
        set(res)
        backgroundTintList = ColorStateList.valueOf(color)
    }
}