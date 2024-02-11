package com.cafe.codechallenge.presentation.common

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.cafe.codechallenge.R
import com.cafe.codechallenge.util.providers.ColorProvider
import com.pixy.codebase.common.CImageView
import com.pixy.codebase.utils.emptyString

/**
 * Created by emadmahouti on 2/8/24
 */

fun CImageView.set(rl: String) {
    this.set(rl, R.drawable.placeholder)
}

fun LinearLayout.center() {
    gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL
}