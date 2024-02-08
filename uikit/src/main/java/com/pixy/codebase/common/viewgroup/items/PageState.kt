package com.pixy.codebase.common.viewgroup.items

/**
 * Created by emadmahouti on 7/7/23
 */
sealed class PageState {
    object NoData: PageState()
    data class Fetching(val fetch: Boolean): PageState()
    data class Failure(val msg: String): PageState()
}