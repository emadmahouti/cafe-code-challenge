package com.cafe.codechallenge.data.remote.util

import com.cafe.codechallenge.data.remote.model.DataHolder
import retrofit2.HttpException
import retrofit2.Response

/**
 * Created by emadmahouti on 2/8/24
 */
class NetworkRunner {
    suspend fun<InnerModel> performCall(call: suspend ()-> Response<InnerModel>): DataHolder<InnerModel> {
        val result =  try {
            val response = call.invoke()
            val body: InnerModel? = response.body()
            if(body != null)
                DataHolder.Success(body)
            else
                DataHolder.Failure(response.code(), response.message())
        }catch (e: java.lang.Exception) {
            if(e is HttpException) {
                DataHolder.Failure(e.code(), e.message())
            } else {
                DataHolder.Failure( -1, "Network problem")
            }
        }

        return result
    }
}