package com.pixy.codebase.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by emadmahouti on 5/14/23
 */
class ToastManager(private val toastData: String) {
    fun show(context: Context) {
        Toast.makeText(context, toastData, Toast.LENGTH_SHORT).show()
    }
}