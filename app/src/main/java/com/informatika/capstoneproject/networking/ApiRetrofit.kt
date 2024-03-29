package com.informatika.capstoneproject.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRetrofit {

    val endpoint:   ApiInterface
    get() {

        val interceptor =   HttpLoggingInterceptor()
        interceptor.level   =   HttpLoggingInterceptor.Level.BODY

        val client  =   OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit    =   Retrofit.Builder()
            .baseUrl("http://192.168.218.72/CapstoneProject/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiInterface::class.java)

    }

}