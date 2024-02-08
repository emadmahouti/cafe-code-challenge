package com.cafe.codechallenge.presentation.common.base

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.cafe.codechallenge.presentation.common.util.AdapterListUpdateCallback
import com.cafe.codechallenge.presentation.common.util.DiffUtilCallback
import com.pixy.codebase.utils.SimpleCallback
import com.pixy.codebase.utils.ThreeArgsCallback

import java.util.*

/**
 * Created by emadmahouti on 2/8/24
 */
abstract class BaseListAdapter<T>: BaseAdapter() {
    protected val items: LinkedList<T> = LinkedList()

    var itemClickListener: ThreeArgsCallback<View, T, Int>? = null

    val isEmpty: Boolean get() = items.isEmpty()

    fun removeData(index: Int) {
        items.removeAt(index)
    }

    fun reset() {
        setupDiffUtil(items) {
            this.items.clear()
        }
    }

    fun addItems(items: List<T>) {
        setupDiffUtil(items) {
            this.items.clear()
            this.items.addAll(items)
        }
    }

    fun addItem(item: T) {
        addItem(item, itemCount)
    }

    fun addItem(item: T, position: Int) {
        addItem(item, position)
    }

    fun getItemAt(position: Int): T {
        return items[position]
    }

    private fun setupDiffUtil(items: List<T>, block: SimpleCallback) {
        val diffCallback = DiffUtilCallback(this.items, items) {
            itemsTheSame { oldItem, newItem ->
                this@BaseListAdapter.areItemsTheSame(oldItem, newItem)
            }
            contentsTheSame { oldItem, newItem ->
                this@BaseListAdapter.areContentTheSame(oldItem, newItem)
            }
        }
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        block.invoke()

        diffResult.dispatchUpdatesTo(AdapterListUpdateCallback(this))
    }


    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean
    abstract fun areContentTheSame(oldItem: T, newItem: T): Boolean

    override fun getItemCount(): Int  {
        return items.size
    }
}