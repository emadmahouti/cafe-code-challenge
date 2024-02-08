package com.cafe.codechallenge.presentation.ui.movieList.items

import android.content.Context
import com.pixy.codebase.common.CImageView
import com.pixy.codebase.common.CTextView
import com.pixy.codebase.common.viewgroup.VLinearLayout
import com.pixy.codebase.extensions.shape
import com.pixy.codebase.providers.ParamsProvider
import com.pixy.codebase.utils.dpToPx

/**
 * Created by emadmahouti on 2/8/24
 */
class BannerItemView(private val context: Context): VLinearLayout(context) {

    private val bannerImageView = CImageView(context, 2f)
    private val bannerTitleView = CTextView(context)

    init {
        addView(bannerImageView, ParamsProvider.Linear.defaultParams())
        addView(bannerTitleView, ParamsProvider.Linear.defaultParams())

        with(bannerImageView) {
            shape(corners = 10.dpToPx)
        }

        with(bannerTitleView) {
            center()
            setTextSizeInPx(12.dpToPx)
        }
    }
}