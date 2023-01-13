package com.example.moviegetter.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private lateinit var retrofitApi: RetrofitApi


    fun getApiService(): RetrofitApi {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val baseURL= "https://www.omdbapi.com"
        val okhttp = OkHttpClient.Builder().addInterceptor(logging)
        retrofitApi= Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okhttp.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
        return retrofitApi
    }
}