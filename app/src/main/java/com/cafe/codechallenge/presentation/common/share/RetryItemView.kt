package com.cafe.codechallenge.presentation.common.share

import android.content.Context
import com.cafe.codechallenge.presentation.common.center
import com.cafe.codechallenge.util.providers.ColorProvider
import com.pixy.codebase.common.CButton
import com.pixy.codebase.common.CTextView
import com.pixy.codebase.common.viewgroup.HLinearLayout
import com.pixy.codebase.common.viewgroup.VLinearLayout
import com.pixy.codebase.extensions.getColor
import com.pixy.codebase.providers.ParamsProvider
import com.pixy.codebase.utils.dpToPx

/**
 * Created by emadmahouti on 2/9/24
 */
class RetryItemView(context: Context): HLinearLayout(context) {

    private val titleView = CTextView(context)
    private val retryButtonView = CButton(context)

    init {
        center()

        addView(titleView, ParamsProvider.Linear.availableWidthParams())
        addView(retryButtonView, ParamsProvider.Linear.wrapContent())

        with(titleView) {
            setOneLiner()
            setTextSizeInPx(16.dpToPx)
            setTextColor(getColor(ColorProvider.textColor))
        }

        with(retryButtonView) {
            text = "Try Again"
            setPadding(16.dpToPx, 8.dpToPx, 16.dpToPx, 8.dpToPx)
        }
    }


    fun set(text: String) {
        titleView.text = text
    }
}