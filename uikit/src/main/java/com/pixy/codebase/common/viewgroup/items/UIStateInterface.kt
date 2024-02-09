package com.pixy.codebase.common.viewgroup.items

/**
 * Created by emadmahouti on 5/12/23
 */
interface UIStateInterface {
    fun showContent()
    fun showRetry(msg: String?)
    fun showLoading()
    fun noData()
}