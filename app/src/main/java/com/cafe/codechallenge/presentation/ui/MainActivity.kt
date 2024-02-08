package com.cafe.codechallenge.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by emadmahouti on 2/8/24
 */
class MainActivity: AppCompatActivity() {
    private var  mainActivityView: MainActivityView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityView = MainActivityView(this)
        setContentView(mainActivityView)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivityView = null
    }
}