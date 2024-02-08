package com.cafe.codechallenge.data.remote.interceptors

import com.cafe.codechallenge.util.addOrIgnoreHeader
import com.cafe.codechallenge.util.asJwt
import com.cafe.codechallenge.util.providers.ConfigProvider
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer

/**
 * Created by emadmahouti on 2/8/24
 */
class HeaderInterceptor(private val configProvider: ConfigProvider): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        with(chain)
        {
            val originalRequest = request()
            val authenticationRequest = request(originalRequest)

            val response = proceed(authenticationRequest)

            val responseBody = response.body ?: return response
            val responseBodyString = responseBody.string()

            val buffer = Buffer()
            originalRequest.body?.writeTo(buffer)

            return response.newBuilder().body(ResponseBody.create(responseBody.contentType(), responseBodyString)).build()
        }
    }

    private fun request(input: Request): Request {

        val builder = input.newBuilder()
        builder.addOrIgnoreHeader(input, "Authorization", configProvider.user_token.asJwt)

        return builder.build()
    }
}