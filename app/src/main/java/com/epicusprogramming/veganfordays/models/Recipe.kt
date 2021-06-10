package com.epicusprogramming.veganfordays.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "recipeResults"
)
data class Recipe(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val aggregateLikes: Int,
    val analyzedInstructions: List<AnalyzedInstruction>,
    val cheap: Boolean,
    val creditsText: String,
    val cuisines: List<String>,
    val dairyFree: Boolean,
    val diets: List<String>,
    val dishTypes: List<String>,
    val gaps: String,
    val glutenFree: Boolean,
    val healthScore: Double,
    val image: String,
    val imageType: String,
    val license: String,
    val lowFodmap: Boolean,
    val nutrition: Nutrition,
    val occasions: List<String>,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularScore: Double,
    val spoonacularSourceUrl: String,
    val summary: String,
    val sustainable: Boolean,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val weightWatcherSmartPoints: Int
): Serializable