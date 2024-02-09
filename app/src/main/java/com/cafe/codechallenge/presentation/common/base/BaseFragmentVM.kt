package com.cafe.codechallenge.presentation.common.base

import androidx.annotation.CallSuper
import com.pixy.codebase.utils.ToastManager

/**
 * Created by emadmahouti on 2/8/24
 */
abstract class BaseFragmentVM<T: BaseViewModel>: BaseFragment() {
    abstract val viewModel: T

    @CallSuper
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