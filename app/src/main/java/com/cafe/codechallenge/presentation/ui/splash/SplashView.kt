package com.cafe.codechallenge.presentation.ui.splash

import android.content.Context
import com.cafe.codechallenge.R
import com.cafe.codechallenge.presentation.common.center
import com.cafe.codechallenge.presentation.common.share.ContainerLayout
import com.cafe.codechallenge.util.providers.ColorProvider
import com.pixy.codebase.common.CImageView
import com.pixy.codebase.common.CProgressView
import com.pixy.codebase.common.viewgroup.VLinearLayout
import com.pixy.codebase.extensions.getColor
import com.pixy.codebase.providers.ParamsProvider
import com.pixy.codebase.providers.margin
import com.pixy.codebase.utils.dpToPx

/**
 * Created by emadmahouti on 2/9/24
 */
class SplashView(context: Context): ContainerLayout(context) {

    private val logoView = CImageView(context)
    private val loadingView = CProgressView(context)

    init {
        setBackgroundColor(getColor(ColorProvider.white))

        setupContent(VLinearLayout(context).apply {
            center()

            addView(logoView, ParamsProvider.Linear(79.dpToPx, 88.dpToPx))
            addView(loadingView, ParamsProvider.Linear.wrapContent().margin(top = 50.dpToPx))

        })

        with(loadingView) {
            setColor(getColor(ColorProvider.primary))
        }

        with(logoView) {
            set(R.drawable.bazaar_logo)
        }
    }
}