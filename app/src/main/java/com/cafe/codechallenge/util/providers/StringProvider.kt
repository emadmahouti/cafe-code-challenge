package com.cafe.codechallenge.util.providers

/**
 * Created by emadmahouti on 2/9/24
 */
object StringProvider {

    val retry get() =
        "Retry"

    val connectionGlitch get() =
        "Connection glitch"

    val poorConnection get() =
        "Seems like there's an internet\n" +
                "connection problem."

    val tryAgain get() =
        "Try again"
}