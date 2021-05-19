package com.epicusprogramming.veganfordays.searchByRecipeResponse

data class Ingredient(
    val foodCategory: String,
    val foodId: String,
    val image: String,
    val text: String,
    val weight: Double
)