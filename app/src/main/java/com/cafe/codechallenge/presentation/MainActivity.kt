package com.cafe.codechallenge.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cafe.codechallenge.util.LifecycleLogObserver

/**
 * Created by emadmahouti on 2/8/24
 */
class MainActivity: AppCompatActivity() {
    private var  mainActivityView: MainActivityView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityView = MainActivityView(this)
        setContentView(mainActivityView)
        LifecycleLogObserver(lifecycle, this::class.java.simpleName)

        if(savedInstanceState == null)
            mainActivityView?.init(supportFragmentManager)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivityView = null
    }
}