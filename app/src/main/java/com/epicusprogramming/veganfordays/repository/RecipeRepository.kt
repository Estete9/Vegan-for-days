package com.epicusprogramming.veganfordays.repository

import com.epicusprogramming.veganfordays.api.RetrofitInstance
import com.epicusprogramming.veganfordays.db.RecipeDatabase

class RecipeRepository(
    val db: RecipeDatabase
) {
    suspend fun searchRecipes(recipeOrIngredientQuery: String, pageNumber: Int) =
        RetrofitInstance.api.getRecipes(recipeOrIngredientQuery)

}