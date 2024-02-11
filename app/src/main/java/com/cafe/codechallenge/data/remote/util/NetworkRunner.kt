package com.cafe.codechallenge.data.remote.util

import android.util.Log
import com.cafe.codechallenge.data.model.DataHolder
import com.cafe.codechallenge.data.model.MappableModel
import retrofit2.HttpException
import retrofit2.Response

/**
 * Created by emadmahouti on 2/8/24
 */
class NetworkRunner {
    suspend inline fun<InnerModel: MappableModel<OuterModel>, OuterModel> performCall(crossinline call: suspend ()-> Response<InnerModel>): DataHolder<OuterModel> {
        val result =  try {
            val response = call.invoke()
            val body: OuterModel? = response.body()?.map()
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