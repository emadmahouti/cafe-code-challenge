package com.pixy.codebase.common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by emadmahouti on 5/12/23
 */
class CRecyclerView(context: Context): RecyclerView(context) {

    fun setAsGrid(spanCount : Int): GridLayoutManager
    {
        val gridLayoutManager = GridLayoutManager(context,spanCount)
        layoutManager = gridLayoutManager

        return gridLayoutManager
    }

    fun setAsVerticalList(reverse: Boolean = false) : LinearLayoutManager
    {
        val linearLayoutManager = LinearLayoutManager(context, VERTICAL, reverse)
        layoutManager = linearLayoutManager

        return linearLayoutManager
    }

    fun setAsHorizontalList(reverse: Boolean = false): LinearLayoutManager
    {
        val linearLayoutManager = LinearLayoutManager(context, HORIZONTAL, reverse)
        layoutManager = linearLayoutManager

        return linearLayoutManager
    }

    fun setHorizontalItemSpace(space: Int) {
        val spaceItemSpaceDecoration = SpaceItemDecoration(space/2,0,space/2, 0)
        addItemDecoration(spaceItemSpaceDecoration)
        setPadding( space/2,0,space/2, 0)

        clipToPadding = false
    }

    fun setVerticalItemSpace(space: Int) {
        val spaceItemSpaceDecoration = SpaceItemDecoration(0,space/2,0,space/2)
        addItemDecoration(spaceItemSpaceDecoration)
        setPadding(0, space/2,0,space/2)

        clipToPadding = false
    }
}

class SpaceItemDecoration : RecyclerView.ItemDecoration
{

    constructor(space: Int) : this(space, space, space, space)

    constructor(left: Int, top: Int, right: Int, bottom: Int)
    {
        this.top = top
        this.left = left
        this.right = right
        this.bottom = bottom
    }

    var top: Int = 0
    var left: Int = 0
    var right: Int = 0
    var bottom: Int = 0


    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    )
    {
        outRect.top = top
        outRect.left = left
        outRect.right = right
        outRect.bottom = bottom
    }
}