package com.epicusprogramming.veganfordays.models

import java.io.Serializable

data class Ingredient(
//    ONLY name needed for converter
    val id: Int,
    val image: String,
    val localizedName: String,
    val name: String
): Serializable