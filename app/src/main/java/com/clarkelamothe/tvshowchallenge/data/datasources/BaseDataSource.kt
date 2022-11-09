package com.clarkelamothe.tvshowchallenge.data.datasources

import retrofit2.Response
import java.io.Serializable

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.success(it)
                }
            }
            return Resource.error(0)
        } catch (e: Exception) {
            return Resource.error(0)
        }
    }
}

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val resId: Int = 0
) : Serializable {

    enum class Status {
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data
            )
        }

        fun <T> error(resId: Int, data: T? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                resId
            )
        }
    }
}