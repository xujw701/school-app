package com.william.schoolapp.data

import retrofit2.Response

suspend fun <T> safeApi(apiCall: suspend () -> T): Result<T, Throwable> =
    try {
        val result = apiCall.invoke()
        if (result is Response<*>) {
            if (result.code() >= 400) {
                val e = Throwable(result.errorBody()?.charStream()?.readText())
                Err(e)
            } else {
                Ok(result)
            }
        } else {
            Ok(result)
        }
    } catch (throwable: Throwable) {
        Err(throwable)
    }