package com.informatika.capstoneproject.networking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiEndpoint {

    private const val BASE_URL  =   "https://newsapi.org/v2/"

    @JvmStatic
    fun getApiClient(): Retrofit{
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}