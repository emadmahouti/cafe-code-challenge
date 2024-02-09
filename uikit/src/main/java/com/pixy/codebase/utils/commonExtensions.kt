package com.pixy.codebase.utils

import android.content.Context
import android.content.res.Configuration

/**
 * Created by emadmahouti on 2/9/24
 */

fun Context.isDarkModeOn(): Boolean {
    return when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES ->
            true // Night mode is active, we're using dark theme
        else ->
            false // Night mode is not active, we're using the light theme

    }
}