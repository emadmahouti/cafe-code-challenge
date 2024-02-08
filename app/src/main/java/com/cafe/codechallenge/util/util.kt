package com.cafe.codechallenge.util

import android.util.Log
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
