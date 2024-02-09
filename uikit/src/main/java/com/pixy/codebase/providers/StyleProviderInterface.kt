package com.pixy.codebase.providers

import com.pixy.codebase.providers.items.ShapeDrawable
import com.pixy.codebase.utils.Color

/**
 * Created by emadmahouti on 6/24/23
 */
interface StyleProviderInterface {
    fun input(): StyleInterface
    fun button(): StyleInterface
}

interface StyleInterface {
    val shapeDrawable: ShapeDrawable
    val textColor: Color
}