package com.cafe.codechallenge.util.providers

import com.cafe.codechallenge.util.asJwt

/**
 * Created by emadmahouti on 2/8/24
 */
class HeaderProvider(private val configProvider: ConfigProvider) {
    private val authHeaderKey = "Authorization"
    private val acceptHeaderKey = "accept"


    fun getHeader(): HashMap<String, String> {
        return hashMapOf(
            authHeaderKey to configProvider.user_token.asJwt,
            acceptHeaderKey to "application/json"
        )
    }
}