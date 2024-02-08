package com.cafe.codechallenge.util.providers

import android.app.UiModeManager
import android.content.Context
import android.content.Context.UI_MODE_SERVICE
import com.cafe.codechallenge.util.elsif

/**
 * Created by emadmahouti on 2/8/24
 */
object ColorProvider {

    data class Color(val light: Int, val dark: Int)

    val primary = Color(-16524603, -16524603)
    val white = Color(-1, -1)
    val black = Color(-16777216, -16524603)


    fun getColor(context: Context, color: Color): Int {
        val mode = context.getSystemService(UI_MODE_SERVICE)
        return elsif(mode == UiModeManager.MODE_NIGHT_YES, color.light, color.dark)
    }
}