package com.pixy.codebase.providers.items

import com.pixy.codebase.utils.Color
import com.pixy.codebase.utils.dpToPx

/**
 * Created by emadmahouti on 5/12/23
 */
data class ShapeDrawable(
    val backgroundColor: Color? = null,
    val borderColor: Color? = null,
    val borderSize: Int = 1.dpToPx,
    val corners: Int? = null
)
