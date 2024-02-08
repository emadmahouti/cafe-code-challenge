package com.cafe.codechallenge.presentation.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.cafe.codechallenge.util.LifecycleLogObserver

/**
 * Created by emadmahouti on 2/8/24
 */
abstract class BaseFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LifecycleLogObserver(lifecycle, this::class.java.simpleName)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LifecycleLogObserver(viewLifecycleOwner.lifecycle, this::class.java.simpleName + "(View)")
        return generateView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewReady()
    }

    fun navigateTo(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }

    abstract fun viewReady()
    abstract fun generateView(): View
}