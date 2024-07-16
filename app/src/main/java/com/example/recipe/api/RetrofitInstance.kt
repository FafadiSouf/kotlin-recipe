package com.example.recipe.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://api.spoonacular.com/"

    private val retrofit by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val api: RecipeApiService by lazy {
        retrofit.create(RecipeApiService::class.java)
    }
}