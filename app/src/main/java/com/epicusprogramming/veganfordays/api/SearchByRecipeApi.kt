package com.epicusprogramming.veganfordays.api

import com.epicusprogramming.veganfordays.models.SearchRecipeResponse
import com.epicusprogramming.veganfordays.util.Constants.Companion.SPOONACULAR_APP_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchByRecipeApi {
    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("query")
        recipeOrIngredientQuery: String,
        @Query("cuisine")
        cuisine: String = "",
        @Query("excludeCuisine")
        excludeCuisine: String = "",
        @Query("diet")
        veganDiet: String = "vegan,vegetarian",
        @Query("intolerances")
        intolerances: String = "",
        @Query("equipment")
        equipment: String = "",
        @Query("type")
        foodType: String = "",
        @Query("instructionsRequired")
        instructionsRequired: Boolean = true,
        @Query("addRecipeInformation")
        addRecipeInformation: Boolean = true,
        @Query("addRecipeNutrition")
        addRecipeNutrition: Boolean = true,
        @Query("sort")
        sort: String = "",
        @Query("apiKey")
        apiKey: String = SPOONACULAR_APP_KEY
    ): Response<SearchRecipeResponse>
}