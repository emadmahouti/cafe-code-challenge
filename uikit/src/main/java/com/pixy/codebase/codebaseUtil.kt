package com.pixy.codebase

import com.pixy.codebase.providers.StyleProviderInterface

/**
 * Created by emadmahouti on 6/24/23
 */

val styleProvider: StyleProviderInterface
get() {
    return Codebase.styleProvider ?: throw java.lang.IllegalStateException("Codebase not initialized")
}