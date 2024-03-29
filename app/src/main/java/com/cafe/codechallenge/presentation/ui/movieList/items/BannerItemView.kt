package com.cafe.codechallenge.presentation.ui.movieList.items

import android.content.Context
import android.widget.ImageView
import com.cafe.codechallenge.domain.model.MovieEntity
import com.cafe.codechallenge.presentation.common.set
import com.cafe.codechallenge.util.image
import com.cafe.codechallenge.util.providers.ColorProvider
import com.pixy.codebase.common.CImageView
import com.pixy.codebase.common.CTextView
import com.pixy.codebase.common.viewgroup.VLinearLayout
import com.pixy.codebase.extensions.getColor
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
            setTextColor(getColor(ColorProvider.textColor))
            setTextSizeInPx(12.dpToPx)
        }
    }

    fun set(item: MovieEntity) {
        bannerImageView.set(item.image)
        bannerTitleView.text = item.title
    }
}