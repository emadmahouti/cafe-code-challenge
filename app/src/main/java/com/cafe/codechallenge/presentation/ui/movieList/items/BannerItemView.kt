package com.cafe.codechallenge.presentation.ui.movieList.items

import android.content.Context
import android.widget.FrameLayout
import android.widget.ImageView
import com.cafe.codechallenge.R
import com.cafe.codechallenge.data.remote.model.MovieResponse
import com.pixy.codebase.common.CImageView
import com.pixy.codebase.common.CTextView
import com.pixy.codebase.common.viewgroup.VLinearLayout
import com.pixy.codebase.extensions.shape
import com.pixy.codebase.providers.ParamsProvider
import com.pixy.codebase.providers.margin
import com.pixy.codebase.utils.dpToPx

/**
 * Created by emadmahouti on 2/8/24
 */
class BannerItemView(private val context: Context): VLinearLayout(context) {

    private val bannerImageView = CImageView(context, 1.29f)
    private val bannerTitleView = CTextView(context)

    init {
        addView(bannerImageView, ParamsProvider.Linear.defaultParams())
        addView(bannerTitleView, ParamsProvider.Linear.defaultParams().margin(top = 10.dpToPx))

        with(bannerImageView) {
            shape(corners = 10.dpToPx)
            scaleType = ImageView.ScaleType.CENTER_CROP
            bannerImageView.clipImage()
        }

        with(bannerTitleView) {
            center()
            setTextSizeInPx(12.dpToPx)
        }
    }

    fun set(item: MovieResponse) {
        bannerImageView.set(item.poster, R.drawable.ic_launcher_foreground)
        bannerTitleView.text = item.title
    }
}