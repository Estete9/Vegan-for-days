package com.epicusprogramming.veganfordays.modelsEdamame

import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "edamamRecipeResults")
data class Hit(
    val _links: LinksX,
    val recipe: Recipe
) : Serializable