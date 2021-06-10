package com.epicusprogramming.veganfordays.models

import java.io.Serializable

data class Step(
//    length not needed for type converter
    val equipment: List<Equipment>,
    val ingredients: List<Ingredient>,
    val length: Length,
    val number: Int,
    val step: String
): Serializable