package com.cafe.codechallenge.data.model

import androidx.annotation.Keep

/**
 * Created by emadmahouti on 2/11/24
 */
@Keep
interface MappableModel<out T> {
    fun map(): T
}