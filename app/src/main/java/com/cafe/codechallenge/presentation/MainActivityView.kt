package com.cafe.codechallenge.presentation

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.cafe.codechallenge.R
import com.cafe.codechallenge.presentation.common.generateStaticViewId
import com.cafe.codechallenge.presentation.common.getColor
import com.cafe.codechallenge.util.providers.ColorProvider
import com.pixy.codebase.providers.ParamsProvider

/**
 * Created by emadmahouti on 2/8/24
 */
class MainActivityView(context: Context): FrameLayout(context) {
    private val fragmentView = FragmentContainerView(context)

    init {
        addView(fragmentView, ParamsProvider.Frame.fullSize())

        with(fragmentView) {
            id = generateStaticViewId()
            setBackgroundColor(getColor(ColorProvider.white))
        }
    }

    fun init(fm: FragmentManager) {
        val navHost = NavHostFragment.create(R.navigation.main_graph)
        fm.beginTransaction()
            .replace(fragmentView.id, navHost)
            .setPrimaryNavigationFragment(navHost) // equivalent to app:defaultNavHost="true"
            .commit()
    }
}