package com.example.healthtracker.model

import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularAPI {
    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("query") query: String,
        @Query("query") number: Int,
        @Query("query") offset: Int,
        @Query("addRecipeInformation") addRecipeInformation: Boolean = true,
        @Query("apiKey") apiKey: String = ApiClient.SPOONACULAR_API_KEY
    ): Recipes
}