package com.cafe.codechallenge.presentation.common

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pixy.codebase.common.CImageView
import com.pixy.codebase.utils.emptyString

/**
 * Created by emadmahouti on 2/8/24
 */

fun TextView.getTextOrNull(): String? = if(this.text.trim() == emptyString) null else this.text.toString()

//fun CImageView.set(rl: String) {
//    this.set(rl, R.drawable.placeholder)
//}

/**
 * static id used for save view state
 */
fun View.generateStaticViewId(): Int =
    this.hashCode()