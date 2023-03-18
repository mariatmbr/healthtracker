package com.example.healthtracker.model

import retrofit2.http.GET

interface SpoonacularAPI {
    @GET("recipes/complexSearch")
    fun getRecipies()
}