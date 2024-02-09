package com.pixy.codebase.common

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.widget.ProgressBar

/**
 * Created by emadmahouti on 2/9/24
 */
class CProgressView(context: Context): ProgressBar(context) {

    fun setColor(color: Int) {
        progressTintList = ColorStateList.valueOf(color)
        indeterminateTintList = ColorStateList.valueOf(color)    }

}