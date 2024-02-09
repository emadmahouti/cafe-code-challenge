package com.cafe.codechallenge.presentation.ui.movieList.items

import android.content.Context
import com.cafe.codechallenge.data.remote.model.MovieResponse
import com.cafe.codechallenge.presentation.common.base.BasePaginationAdapter
import com.cafe.codechallenge.presentation.common.base.BaseViewHolder
import com.pixy.codebase.extensions.setCClickListener
import com.pixy.codebase.providers.ParamsProvider
import com.pixy.codebase.utils.OneArgCallback

/**
 * Created by emadmahouti on 2/8/24
 */
class MovieListAdapter : BasePaginationAdapter<MovieResponse>(onBindingPosition = 3) {
    var paginationCallback: OneArgCallback<Int> by this::callback

    override fun createViewHolder(context: Context, viewType: Int): BaseViewHolder {
        return BaseViewHolder(BannerItemView(context))
    }

    override fun bind(holder: BaseViewHolder, position: Int) {
        super.bind(holder, position)

        val itemView = holder.itemView
        if (itemView is BannerItemView) {
            val item = getItemAt(position)

            itemView.setCClickListener { itemClickListener?.invoke(itemView, item, holder.absoluteAdapterPosition) }
            itemView.set(item)
        }
    }

    override fun areContentTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
        return oldItem.id == newItem.id
    }
}