package com.epicusprogramming.veganfordays.models

import java.io.Serializable

data class Nutrition(
    val caloricBreakdown: CaloricBreakdown,
    val flavanoids: List<Flavanoid>,
    val ingredients: List<IngredientX>,
    val nutrients: List<NutrientX>,
    val properties: List<Property>,
    val weightPerServing: WeightPerServing
): Serializable