package com.example.healthtracker.model

data class Recipe(
    val id: Int,
    val title: String,
    val readyInMinutes: Int,
    val sourceUrl: String,
    val image: String,
)

data class Recipes(
    val results: List<Recipe>,
)
