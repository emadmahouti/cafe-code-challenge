package com.cafe.codechallenge.presentation.base

import androidx.lifecycle.ViewModel
import com.pixy.codebase.common.viewgroup.items.PageState
import com.pixy.codebase.utils.ToastManager

/**
 * Created by emadmahouti on 2/8/24
 */
abstract class BaseFragmentVM<T: ViewModel>: BaseFragment() {
    abstract val viewModel: T

    override fun viewReady() {

    }

    open fun toast(message: String) {
        ToastManager(message).show(requireContext())
    }

    open fun pageState(state: PageState) {}
    abstract fun observe()
}