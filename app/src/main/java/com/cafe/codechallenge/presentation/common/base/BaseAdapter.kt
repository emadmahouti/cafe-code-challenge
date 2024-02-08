package com.cafe.codechallenge.presentation.common.base

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by emadmahouti on 2/8/24
 */
abstract class BaseAdapter: RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return createViewHolder(parent.context, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bind(holder, position)
    }

    abstract fun createViewHolder(context: Context, viewType: Int): BaseViewHolder

    abstract fun bind(holder: BaseViewHolder, position: Int)
}