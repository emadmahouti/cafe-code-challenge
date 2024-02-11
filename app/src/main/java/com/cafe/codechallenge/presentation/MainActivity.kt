package com.cafe.codechallenge.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.cafe.codechallenge.R
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
            init(mainActivityView?.fragmentView?.id!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivityView = null
    }

    @SuppressLint("CommitTransaction")
    private fun init(id: Int) {
        val navHost = NavHostFragment.create(R.navigation.main_graph)
        supportFragmentManager.beginTransaction().apply {
            replace(id, navHost)
            setPrimaryNavigationFragment(navHost) // equivalent to app:defaultNavHost="true"
            this.commit()
        }
    }
}