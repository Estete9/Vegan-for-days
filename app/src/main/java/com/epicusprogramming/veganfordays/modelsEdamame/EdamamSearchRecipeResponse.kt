package com.epicusprogramming.veganfordays.modelsEdamame

data class EdamamSearchRecipeResponse(
    val _links: Links,
    val count: Int,
    val from: Int,
    val hits: MutableList<Hit>,
    val to: Int
)