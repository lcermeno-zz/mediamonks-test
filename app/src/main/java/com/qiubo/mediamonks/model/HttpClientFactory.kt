package com.qiubo.mediamonks.model

import com.qiubo.mediamonks.BuildConfig
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory


object HttpClientFactory {

    private val mRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
    }

    fun <T> getService(serviceClass: Class<T>): T = mRetrofit.create(serviceClass)
}