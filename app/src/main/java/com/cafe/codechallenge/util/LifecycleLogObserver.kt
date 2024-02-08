package com.cafe.codechallenge.util

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
 * Created by emadmahouti on 2/8/24
 */
class LifecycleLogObserver(lifecycle: Lifecycle, private val className: String):
    DefaultLifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        log("ON_CREATE")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        log("ON_DESTROY")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        log("ON_PAUSE")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        log("ON_RESUME")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        log("ON_START")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        log("ON_STOP")
    }

    private fun log(message: String) {
        log("lifecycle:", "$className --- $message")
    }
}