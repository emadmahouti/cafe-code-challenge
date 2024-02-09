package com.pixy.codebase.providers

import android.annotation.SuppressLint
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlin.reflect.KProperty

/**
 * Created by emadmahouti on 5/12/23
 */
class ViewProvider<T: View>(private val useCash: Boolean, private val initialize: () -> T) {
    private var theView: T? = null

    @SuppressLint("SuspiciousIndentation")
    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        val viewHolder = theView ?: ((thisRef as? Fragment)?.view) as? T
           return viewHolder ?: kotlin.run {
                initialize().also { initializedView ->
                    if(useCash) {
                        theView = initializedView
                    }
                }
            }
    }

    fun lifecycleAware(lifecycle: Lifecycle) {
        lifecycle.addObserver(object: DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                theView = null
            }
        })
    }
}