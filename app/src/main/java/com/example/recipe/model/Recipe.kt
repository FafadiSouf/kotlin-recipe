package com.example.recipe.model

data class Recipe(
    val id: Int,
    val title: String,
    val image: String,
    val ingredients: List<String>
)
