package com.cafe.codechallenge.presentation.ui.movieList.items

import android.content.Context
import android.widget.Space
import androidx.core.view.setPadding
import com.cafe.codechallenge.R
import com.cafe.codechallenge.presentation.common.center
import com.pixy.codebase.common.*
import com.pixy.codebase.common.viewgroup.HLinearLayout
import com.pixy.codebase.providers.ParamsProvider
import com.pixy.codebase.utils.dpToPx

/**
 * Created by emadmahouti on 2/9/24
 */
class MovieTitleView(context: Context): CConstraintLayout(context) {

    private val titleView = CImageView(context)
    private val logoView = CImageView(context)

    init {
        addView(titleView, ParamsProvider.Linear.wrapContent())
        addView(logoView, ParamsProvider.Linear(38.dpToPx, 41.dpToPx))


        constraint {
            titleView.cLeft connectToParent cLeft
            titleView.cRight connectToParent cRight
            titleView.cBottom connect logoView.cBottom
            titleView.cTop connect logoView.cTop

            logoView.cRight connectToParent cRight margin 15.dpToPx
            logoView.cBottom connectToParent cBottom
            logoView.cTop connectToParent cTop margin 10.dpToPx
        }

        with(logoView) {
            set(R.drawable.bazaar_logo)
        }

        with(titleView) {
            set(R.drawable.discover)
        }

    }

}