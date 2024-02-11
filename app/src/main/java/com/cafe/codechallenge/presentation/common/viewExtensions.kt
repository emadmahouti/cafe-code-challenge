package com.cafe.codechallenge.presentation.common

import android.view.Gravity
import android.widget.LinearLayout
import com.cafe.codechallenge.R
import com.pixy.codebase.common.CImageView

/**
 * Created by emadmahouti on 2/8/24
 */

fun CImageView.set(rl: String) {
    this.set(rl, R.drawable.placeholder)
}

fun LinearLayout.center() {
    gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL
}