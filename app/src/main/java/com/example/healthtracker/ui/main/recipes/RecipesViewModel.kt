package com.example.healthtracker.ui.main.recipes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthtracker.model.ApiClient
import com.example.healthtracker.model.Recipe
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {
    val recipes: MutableLiveData<List<Recipe>> = MutableLiveData()

    fun getRecipes(query: String) {
        Log.d("qwe", "query $query")
        viewModelScope.launch {
            recipes.value =
                ApiClient.spoonacular.getRecipes(query, 20, 0).results
        }
    }
}