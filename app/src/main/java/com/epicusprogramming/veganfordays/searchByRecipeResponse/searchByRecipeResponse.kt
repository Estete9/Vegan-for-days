package com.epicusprogramming.veganfordays.searchByRecipeResponse

data class searchByRecipeResponse(
    val count: Int,
    val from: Int,
    val hits: List<Hit>,
    val more: Boolean,
    val q: String,
    val to: Int
)