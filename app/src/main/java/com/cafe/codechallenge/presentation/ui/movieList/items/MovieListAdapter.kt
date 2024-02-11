package com.cafe.codechallenge.presentation.ui.movieList.items

import android.content.Context
import com.cafe.codechallenge.domain.model.MovieEntity
import com.cafe.codechallenge.presentation.common.base.BasePaginationAdapter
import com.cafe.codechallenge.presentation.common.base.BaseViewHolder
import com.pixy.codebase.extensions.setCClickListener
import com.pixy.codebase.utils.OneArgCallback

/**
 * Created by emadmahouti on 2/8/24
 */
class MovieListAdapter : BasePaginationAdapter<MovieEntity>(onBindingPosition = 6) {
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

    override fun areContentTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem.id == newItem.id
    }
}