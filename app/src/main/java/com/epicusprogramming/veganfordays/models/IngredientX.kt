package com.epicusprogramming.veganfordays.models

import java.io.Serializable

data class IngredientX(
    val amount: Double,
    val id: Int,
    val name: String,
    val nutrients: List<Nutrient>,
    val unit: String

): Serializable