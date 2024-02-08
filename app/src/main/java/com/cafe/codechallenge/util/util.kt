package com.cafe.codechallenge.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pixy.codebase.common.viewgroup.items.PageState
import okhttp3.Request

/**
 * Created by emadmahouti on 2/8/24
 */

fun log(key: String, message: Any) {
    Log.d(key, message.toString())
}

fun Request.Builder.addOrIgnoreHeader(request: Request, name: String, value: String): Request.Builder {
    if(request.header(name) == null)
        addHeader(name, value)

    return this
}

val String.asJwt: String get(){
    return "Bearer $this"
}

fun <X> elsif(b: Boolean, x1: X, x2: X): X {
    if (b)
        return x1
    return x2
}

typealias StateLiveData = MutableLiveData<PageState>