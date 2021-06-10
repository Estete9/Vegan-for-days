package com.epicusprogramming.veganfordays.models

import java.io.Serializable

data class NutrientX(
    val amount: Double,
    val name: String,
    val percentOfDailyNeeds: Double,
    val title: String,
    val unit: String
): Serializable