package com.cafe.codechallenge.presentation.ui.movieList

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.cafe.codechallenge.R
import com.cafe.codechallenge.domain.model.MovieEntity
import com.cafe.codechallenge.presentation.common.share.ConcatWithLoadingAdapter
import com.cafe.codechallenge.presentation.ui.movieList.items.MovieListAdapter
import com.cafe.codechallenge.util.bazaarSmallLogo
import com.cafe.codechallenge.util.providers.ColorProvider
import com.pixy.codebase.common.*
import com.pixy.codebase.common.viewgroup.items.UIStateInterface
import com.pixy.codebase.extensions.getColor
import com.pixy.codebase.providers.ParamsProvider
import com.pixy.codebase.utils.OneArgCallback
import com.pixy.codebase.utils.SimpleCallback
import com.pixy.codebase.utils.ThreeArgsCallback
import com.pixy.codebase.utils.dpToPx

/**
 * Created by emadmahouti on 2/8/24
 */
class MovieListView(private val context: Context): CConstraintLayout(context), UIStateInterface {

    private val titleView = CImageView(context)
    private val logoView = CImageView(context)
    private val recyclerView = CRecyclerView(context)
    private val listAdapter = MovieListAdapter()
    private val concatAdapter = ConcatWithLoadingAdapter(listAdapter)

    var paginationCallback: OneArgCallback<Int> by listAdapter::paginationCallback
    var itemClickListener: ThreeArgsCallback<View, MovieEntity, Int>? by listAdapter::itemClickListener

    init {
        val sideMargin = 15.dpToPx
        val cellCount = 3

        setWillNotDraw(false)

        addView(recyclerView, ParamsProvider.Constraint.availableHeightParams())
        addView(titleView, ParamsProvider.Constraint.wrapContent())
        addView(logoView, ParamsProvider.Constraint(38.dpToPx, 41.dpToPx))

        constraint {
            titleView.cLeft connectToParent cLeft
            titleView.cRight connectToParent cRight
            titleView.cBottom connect logoView.cBottom
            titleView.cTop connect logoView.cTop

            logoView.cRight connectToParent cRight margin 15.dpToPx
            logoView.cTop connectToParent cTop margin 10.dpToPx

            recyclerView.cTop connect logoView.cBottom margin sideMargin
            recyclerView.cLeft connectToParent cLeft
            recyclerView.cRight connectToParent cRight
            recyclerView.cBottom connectToParent cBottom
        }

        with(logoView) {
            set(R.drawable.bazaar_logo)
            transitionName = bazaarSmallLogo
        }

        with(titleView) {
            set(R.drawable.discover)
        }

        with(recyclerView) {
            id = R.id.movie_list
            setAsGrid(cellCount).spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position + 1 == adapter?.itemCount)
                        cellCount
                    else
                        1
                }
            }
            setVerticalItemSpace(sideMargin)
            setHorizontalItemSpace(sideMargin)
            adapter = concatAdapter.concat()
        }
    }

    fun set(items: List<MovieEntity>) {
        listAdapter.addItems(items)
    }

    override fun showContent() {
        concatAdapter.showContent()
    }

    override fun showRetry(msg: String?, block: SimpleCallback) {
        concatAdapter.showRetry(msg, block)
    }

    override fun showLoading() {
        concatAdapter.showLoading()
    }

    override fun noData() {
        concatAdapter.showContent()
    }


    /***
     * Add a shine shadow behind the all the child in viewGroup
     */
    private val shadowPaint by lazy {
        Paint().apply {
            this.style = Paint.Style.FILL
            this.color = getColor(ColorProvider.shadowColor)
            this.setShadowLayer(80f, 0f, 0f, getColor(ColorProvider.shadowColor))
            this.isAntiAlias = true
            this.alpha = 20
            xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)
        }
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val radios = 200.dpToPx.toFloat()

        val cx = width - width / 3f
        val cy = height / 4.5f

        canvas.drawCircle(cx, cy, radios, shadowPaint)
    }
}