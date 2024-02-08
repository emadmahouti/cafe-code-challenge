package com.pixy.codebase.common

import android.content.Context
import android.text.TextUtils
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import com.pixy.codebase.utils.dpToPx

/**
 * Created by emadmahouti on 5/12/23
 */
open class CTextView(context: Context): AppCompatTextView(context) {
    init {
        setTextSizeInPx(14.dpToPx)
    }

    fun setTextSizeInPx(size: Int) {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, size.toFloat())
    }

    fun setTextSizeInPx(size: Float) {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
    }

    fun setOneLiner()
    {
        ellipsize = TextUtils.TruncateAt.END
        setSingleLine()
        maxLines = 1
    }

    fun linesFixed(count : Int)
    {
        ellipsize = TextUtils.TruncateAt.END
        setLines(count)
    }
}
