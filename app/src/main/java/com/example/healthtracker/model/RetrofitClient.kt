package com.example.healthtracker.model

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitClient {
    private const val SPOONACULAR_API_KEY = "d0fa23931bb446ea984403013621b9c2"
    private const val SPOONACULAR_URL = "https://api.spoonacular.com"

    fun getSpoonacularClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(SPOONACULAR_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor {
                val builder = it.request().newBuilder()
                builder.header("X-RapidAPI-Key", SPOONACULAR_API_KEY)
                return@addInterceptor it.proceed(builder.build())
            }.build())
            .build()


}