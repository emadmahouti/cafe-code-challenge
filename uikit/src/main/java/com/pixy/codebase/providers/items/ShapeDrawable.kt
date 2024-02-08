package com.pixy.codebase.providers.items

import com.pixy.codebase.utils.dpToPx

/**
 * Created by emadmahouti on 5/12/23
 */
data class ShapeDrawable(
    val backgroundColor: Int? = null,
    val borderColor: Int? = null,
    val borderSize: Int = 1.dpToPx,
    val corners: Int? = null
)
