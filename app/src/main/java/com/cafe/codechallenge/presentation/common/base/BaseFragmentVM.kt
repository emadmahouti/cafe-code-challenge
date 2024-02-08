package com.cafe.codechallenge.presentation.common.base

import com.pixy.codebase.utils.ToastManager

/**
 * Created by emadmahouti on 2/8/24
 */
abstract class BaseFragmentVM<T: BaseViewModel>: BaseFragment() {
    abstract val viewModel: T

    override fun viewReady() {
        with(viewModel) {
            toastLiveData.observe(viewLifecycleOwner) { message ->
                toast(message)
            }
            observe()
        }
    }
    open fun toast(message: String) {
        ToastManager(message).show(requireContext())
    }
    abstract fun observe()
}