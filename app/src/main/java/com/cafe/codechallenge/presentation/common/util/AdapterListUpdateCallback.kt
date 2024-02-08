package com.cafe.codechallenge.presentation.common.util

import androidx.recyclerview.widget.ListUpdateCallback
import com.cafe.codechallenge.presentation.common.base.BaseAdapter

/**
 * Created by emadmahouti on 2/8/24
 */
class AdapterListUpdateCallback(private val adapter: BaseAdapter) : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {
        if (position == 0)
            adapter.notifyDataSetChanged()
        else
            adapter.notifyItemRangeInserted(position, count)
    }

    override fun onRemoved(position: Int, count: Int) {
        adapter.notifyItemRangeRemoved(position, count)
    }

    override fun onMoved(fromPosition: Int, toPosition: Int) {
        adapter.notifyItemMoved(fromPosition, toPosition)
    }

    override fun onChanged(position: Int, count: Int, payload: Any?) {
        adapter.notifyItemRangeChanged(position, count, payload)
    }
}