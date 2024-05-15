package com.william.schoolapp.data

import okhttp3.Interceptor
import okhttp3.Response

class ResInterceptor(private val resId: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        // Add the resource_id query parameter to the original URL
        val urlWithResId = originalUrl.newBuilder()
            .addQueryParameter("resource_id", resId)
            .build()

        // Create a new request with the updated URL
        val requestWithResId = originalRequest.newBuilder()
            .url(urlWithResId)
            .build()

        return chain.proceed(requestWithResId)
    }
}