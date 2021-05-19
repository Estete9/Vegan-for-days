package com.epicusprogramming.veganfordays.api

import com.epicusprogramming.veganfordays.searchByRecipeResponse.searchByRecipeResponse
import com.epicusprogramming.veganfordays.util.Constants.Companion.APP_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface searchByRecipeApi {
    @GET("search")
    suspend fun getRecipes(
        @Query("q")
        recipeOrIngredient: String,
        @Query("health")
        veganDiet: String = "vegan",
        @Query("app_id")
        app_id: String = "1e7c5a9d",
        @Query("app_key")
        app_key: String = APP_KEY
    ): Response<searchByRecipeResponse>
}