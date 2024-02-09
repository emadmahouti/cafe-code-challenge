package com.cafe.codechallenge.presentation.common.share

import android.content.Context
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ConcatAdapter
import com.cafe.codechallenge.presentation.common.base.BaseAdapter
import com.cafe.codechallenge.presentation.common.base.BaseViewHolder
import com.cafe.codechallenge.util.elsif
import com.cafe.codechallenge.util.providers.ColorProvider
import com.pixy.codebase.common.CProgressView
import com.pixy.codebase.providers.ParamsProvider
import com.pixy.codebase.utils.getColor

/**
 * Created by emadmahouti on 2/8/24
 */
class ConcatWithLoadingAdapter(private val adapter: BaseAdapter) {
    enum class ViewType(val value: Int) {
        RETRY(-1),
        LOADING(1),
        NOTHING(0)
    }

    data class LoadingModel(val type: ViewType, val visibility: Boolean, val extra: Any? = null)
    private val loadingAdapter = LoadingAdapter()

    fun concat(): ConcatAdapter {
        return ConcatAdapter(adapter, loadingAdapter)
    }

    fun showLoading() {
        loadingAdapter.changeItem(LoadingModel(ViewType.LOADING, true))
        loadingAdapter.notifyItemChanged(this.adapter.itemCount)
    }

    fun showContent() {
        loadingAdapter.changeItem(LoadingModel(ViewType.NOTHING, false))
        loadingAdapter.notifyItemChanged(this.adapter.itemCount)
    }

    fun showRetry(message: String?) {
        loadingAdapter.changeItem(LoadingModel(ViewType.RETRY, true, message))
        loadingAdapter.notifyItemChanged(this.adapter.itemCount)
    }

    class LoadingAdapter : BaseAdapter() {

        private var item = arrayListOf<LoadingModel>()
        fun changeItem(item: LoadingModel) {
            clear()
            this.item.add(item)
        }

        fun clear() {
            item.clear()
        }

        override fun getItemViewType(position: Int): Int {
            return item.first().type.value
        }

        override fun createViewHolder(context: Context, viewType: Int): BaseViewHolder {
            val itemView =
                when (viewType) {
                    ViewType.LOADING.value -> {
                        CProgressView(context).also {
                            it.layoutParams = ParamsProvider.Linear.defaultParams()
                            it.setColor(getColor(context, ColorProvider.primary))
                        }
                    }
                    ViewType.RETRY.value -> {
                        RetryItemView(context).also {
                            it.layoutParams = ParamsProvider.Linear.defaultParams()
                        }
                    }
                    else -> {
                        View(context)
                    }
                }

            return BaseViewHolder(itemView)
        }

        override fun bind(holder: BaseViewHolder, position: Int) {
            val itemView = holder.itemView
            val item = item[holder.bindingAdapterPosition]

            itemView.isVisible = item.visibility

            if(itemView is RetryItemView && item.extra as? String != null) {
                itemView.set(item.extra)
            }
        }

        override fun getItemCount(): Int = 1
    }
}