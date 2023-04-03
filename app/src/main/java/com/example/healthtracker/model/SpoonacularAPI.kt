package com.example.healthtracker.model

import retrofit2.http.GET

interface SpoonacularAPI {
    @GET("savedIdeas/complexSearch")
    fun getRecipies()
}