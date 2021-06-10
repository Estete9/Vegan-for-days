package com.epicusprogramming.veganfordays.models

data class SearchRecipeResponse(
    val number: Int,
    val offset: Int,
    val results: MutableList<Recipe>,
    val totalResults: Int
)