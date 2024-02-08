package com.pixy.codebase.common

import android.content.Context
import android.view.Gravity
import com.pixy.codebase.extensions.shape
import com.pixy.codebase.providers.StyleInterface
import com.pixy.codebase.utils.dpToPx
import com.pixy.codebase.providers.StyleProviderInterface
import com.pixy.codebase.styleProvider

/**
 * Created by emadmahouti on 5/12/23
 */
open class CButton(context: Context): CTextView(context) {
    private val buttonHeight = 48.dpToPx

    init {
        setStyle(styleProvider.button())

        gravity = Gravity.CENTER
        textAlignment = TEXT_ALIGNMENT_CENTER
    }

    fun setStyle(style: StyleInterface) {
        val shapeDrawable = style.shapeDrawable
        setTextColor(style.textColor)
        shape(
            shapeDrawable.backgroundColor,
            shapeDrawable.borderColor,
            shapeDrawable.borderSize,
            shapeDrawable.corners
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(
            widthMeasureSpec,
            MeasureSpec.makeMeasureSpec(buttonHeight, MeasureSpec.EXACTLY)
        )
    }
}