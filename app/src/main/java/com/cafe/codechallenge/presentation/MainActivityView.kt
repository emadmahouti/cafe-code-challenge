package com.cafe.codechallenge.presentation

import android.content.Context
import android.widget.FrameLayout
import androidx.fragment.app.FragmentContainerView
import com.cafe.codechallenge.R
import com.cafe.codechallenge.util.providers.ColorProvider
import com.pixy.codebase.extensions.getColor
import com.pixy.codebase.providers.ParamsProvider

/**
 * Created by emadmahouti on 2/8/24
 */
class MainActivityView(context: Context): FrameLayout(context) {
    val fragmentView = FragmentContainerView(context)

    init {
        addView(fragmentView, ParamsProvider.Frame.fullSize())

        with(fragmentView) {
            id = R.id.main_frame
            setBackgroundColor(getColor(ColorProvider.backgroundColor))
        }
    }
}