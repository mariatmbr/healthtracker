package com.example.healthtracker.model

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val SPOONACULAR_API_KEY = "d0fa23931bb446ea984403013621b9c2"
    private const val SPOONACULAR_URL = "https://api.spoonacular.com"

    val spoonacular by lazy {
        Retrofit.Builder().baseUrl(SPOONACULAR_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(SpoonacularAPI::class.java)
    }

}