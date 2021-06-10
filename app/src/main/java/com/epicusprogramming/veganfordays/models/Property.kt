package com.epicusprogramming.veganfordays.models

import java.io.Serializable

data class Property(
    val amount: Double,
    val name: String,
    val title: String,
    val unit: String
): Serializable