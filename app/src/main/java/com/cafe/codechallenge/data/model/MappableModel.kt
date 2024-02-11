package com.cafe.codechallenge.data.model

/**
 * Created by emadmahouti on 2/11/24
 */
interface MappableModel<out T> {
    fun map(): T
}