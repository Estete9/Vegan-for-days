package com.epicusprogramming.veganfordays.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.epicusprogramming.veganfordays.modelsEdamame.Hit

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(hit: Hit): Long

    @Query("SELECT * FROM edamamRecipeResults")
    fun getAllRecipes(): LiveData<List<Hit>>

    @Delete
    suspend fun deleteRecipe(hit: Hit)
}