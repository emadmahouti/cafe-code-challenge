package com.cafe.codechallenge.presentation.common.base

import com.pixy.codebase.utils.OneArgCallback


/**
 * Created by emadmahouti on 5/12/23
 */
abstract class BasePaginationAdapter<T>(private val onBindingPosition: Int = 1): BaseListAdapter<T>() {
    private var page = 1
    private var totalPage = 1

    var callback: OneArgCallback<Int> = {}

    override fun bind(holder: BaseViewHolder, position: Int) {
        if(itemCount - onBindingPosition == holder.absoluteAdapterPosition) {
            callback(++page)
        }
    }
}