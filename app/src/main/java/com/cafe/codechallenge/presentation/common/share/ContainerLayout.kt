package com.cafe.codechallenge.presentation.common.share

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.pixy.codebase.common.CButton
import com.pixy.codebase.common.CTextView
import com.pixy.codebase.common.viewgroup.items.UIStateInterface
import com.pixy.codebase.extensions.hide
import com.pixy.codebase.extensions.show
import com.pixy.codebase.providers.ParamsProvider
import com.pixy.codebase.utils.SimpleCallback

/**
 * Created by emadmahouti on 5/12/23
 */

open class ContainerLayout(context: Context) :
    FrameLayout(context), UIStateInterface {

    enum class State {
        CONTENT,
        RETRY,
        LOADING,
        NO_DATA
    }

    private var content: View? = null
    private var loadingView = ProgressBar(context)
    private val retryView = RetryView(context)
    private val noDataView = CTextView(context)

    protected var currentState: State = State.CONTENT

    fun setupContent(view: View) {
        this.content = view

        addView(content, ParamsProvider.Frame.fullSize())
        addView(
            loadingView,
            ParamsProvider.Frame.wrapContent()
                .gravity(Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL)
        )
        addView(
            retryView,
            ParamsProvider.Frame.wrapContent()
                .gravity(Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL)
        )

        addView(
            noDataView,
            ParamsProvider.Frame.wrapContent()
                .gravity(Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL)
        )

        with(noDataView) {
            text = "NO DATA"
        }

        showContent()
    }

    override fun showContent() {
        currentState = State.CONTENT
        hideAll()
        content?.show()
    }

    override fun showRetry(msg: String?, block: SimpleCallback) {
        currentState = State.RETRY
        hideAll()
        retryView.show()
        retryView.retryClickListener = {
            block.invoke()
        }
    }

    override fun showLoading() {
        currentState = State.LOADING
        hideAll()
        loadingView.show()
    }

    override fun noData() {
        currentState = State.NO_DATA
        hideAll()
        noDataView.show()
    }

    private fun hideAll() {
        content?.hide()
        retryView.hide()
        loadingView.hide()
        noDataView.hide()
    }
}