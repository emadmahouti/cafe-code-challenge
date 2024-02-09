package com.cafe.codechallenge.presentation.common.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import com.pixy.codebase.common.CProgressView
import com.pixy.codebase.common.CTextView
import com.pixy.codebase.providers.ParamsProvider

/**
 * Created by emadmahouti on 2/8/24
 */
class ConcatWithLoadingAdapter(private val adapter: BaseAdapter) {

    private val loadingAdapter = LoadingAdapter()

    fun concat(): ConcatAdapter {
        return ConcatAdapter(adapter, loadingAdapter)
    }

    fun showLoading() {
        loadingAdapter.flag = 1
        invalidate()
    }

    fun showContent() {
        loadingAdapter.flag = 0
        invalidate()
    }

    fun showRetry() {
    }

    private fun invalidate() {
        loadingAdapter.notifyItemChanged(0)
    }

    inner class LoadingAdapter : BaseAdapter() {
        var flag: Int = 0

        override fun createViewHolder(context: Context, viewType: Int): BaseViewHolder {
            return BaseViewHolder(CProgressView(context).also {
                it.layoutParams = ParamsProvider.Linear.defaultParams()
            })
        }

        override fun bind(holder: BaseViewHolder, position: Int) {
            val itemView = holder.itemView
            if(flag == 0) {
                itemView.visibility = View.GONE
            } else {
                itemView.visibility = View.VISIBLE
            }
        }

        override fun getItemCount(): Int = 1
    }
}