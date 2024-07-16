package com.example.recipe.api


import com.example.recipe.model.Recipe
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiService {

    @GET("recipes/complexSearch")
    fun getRecipes(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String
    ): Call<RecipeResponse>
}

data class RecipeResponse(
    val results: List<Recipe>
)