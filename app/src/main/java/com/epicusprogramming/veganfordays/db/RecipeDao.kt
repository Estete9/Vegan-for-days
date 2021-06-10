package com.epicusprogramming.veganfordays.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.epicusprogramming.veganfordays.models.Recipe

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(recipe: Recipe): Long

    @Query("SELECT * FROM recipeResults")
    fun getAllRecipes() : LiveData<List<Recipe>>

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)
}