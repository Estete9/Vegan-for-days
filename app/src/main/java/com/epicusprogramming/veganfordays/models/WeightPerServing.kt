package com.epicusprogramming.veganfordays.models

import java.io.Serializable

data class WeightPerServing(
    val amount: Int,
    val unit: String
): Serializable