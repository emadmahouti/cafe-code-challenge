package com.pixy.codebase

import com.pixy.codebase.providers.StyleProviderInterface

/**
 * Created by emadmahouti on 6/24/23
 */
object Codebase {
    internal var styleProvider: StyleProviderInterface? = null

    fun init(styleProviderInterface: StyleProviderInterface) {
        this.styleProvider = styleProviderInterface
    }
}