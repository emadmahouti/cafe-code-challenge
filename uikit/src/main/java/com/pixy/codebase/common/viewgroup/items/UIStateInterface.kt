package com.pixy.codebase.common.viewgroup.items

import com.pixy.codebase.utils.SimpleCallback

/**
 * Created by emadmahouti on 5/12/23
 */
interface UIStateInterface {
    fun showContent()
    fun showRetry(msg: String?, block: SimpleCallback)
    fun showLoading()
    fun noData()
}