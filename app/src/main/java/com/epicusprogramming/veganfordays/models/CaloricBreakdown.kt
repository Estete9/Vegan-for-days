package com.epicusprogramming.veganfordays.models

import java.io.Serializable

data class CaloricBreakdown(
    val percentCarbs: Double,
    val percentFat: Double,
    val percentProtein: Double
): Serializable