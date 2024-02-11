package com.cafe.codechallenge.presentation.common.share

import android.content.Context
import com.cafe.codechallenge.R
import com.cafe.codechallenge.presentation.common.center
import com.cafe.codechallenge.util.providers.ColorProvider
import com.cafe.codechallenge.util.providers.SizeProvider
import com.cafe.codechallenge.util.providers.StringProvider
import com.cafe.codechallenge.util.providers.StyleProvider
import com.pixy.codebase.common.CButton
import com.pixy.codebase.common.CIconView
import com.pixy.codebase.common.CTextView
import com.pixy.codebase.common.viewgroup.VLinearLayout
import com.pixy.codebase.extensions.getColor
import com.pixy.codebase.extensions.setCClickListener
import com.pixy.codebase.providers.ParamsProvider
import com.pixy.codebase.providers.margin
import com.pixy.codebase.utils.SimpleCallback
import com.pixy.codebase.utils.dpToPx

/**
 * Created by emadmahouti on 2/9/24
 */
class RetryView(context: Context): VLinearLayout(context) {

    private val imageView = CIconView(context)
    private val titleView = CTextView(context)
    private val subTitleView = CTextView(context)
    private val retryButtonView = CButton(context)

    var retryClickListener: SimpleCallback? = null

    init {
        center()

        addView(imageView, ParamsProvider.Linear.wrapContent())
        addView(titleView, ParamsProvider.Linear.wrapContent().margin(top = SizeProvider.size4X))
        addView(subTitleView, ParamsProvider.Linear.wrapContent().margin(top = SizeProvider.size2X))
        addView(retryButtonView, ParamsProvider.Linear.wrapContent().margin(top = SizeProvider.size8X))

        with(imageView) {
            set(R.drawable.sad)
        }

        with(subTitleView) {
            center()
            text = StringProvider.poorConnection
            setTextSizeInPx(14.dpToPx)
            setTextColor(getColor(ColorProvider.subtitleColor))
        }

        with(titleView) {
            setTextSizeInPx(16.dpToPx)
            setTextColor(getColor(ColorProvider.textColor))
            text = StringProvider.connectionGlitch
        }

        with(retryButtonView) {
            setPadding(SizeProvider.size8X, SizeProvider.size3X, SizeProvider.size8X, SizeProvider.size3X)
            setStyle(StyleProvider.RetryButton())
            text = StringProvider.retry
            setCClickListener { retryClickListener?.invoke() }
        }
    }
}