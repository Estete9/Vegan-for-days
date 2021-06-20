package com.epicusprogramming.veganfordays.api

import com.epicusprogramming.veganfordays.modelsEdamame.EdamamSearchRecipeResponse
import com.epicusprogramming.veganfordays.util.Constants.Companion.APP_ID_EDAMAM
import com.epicusprogramming.veganfordays.util.Constants.Companion.APP_KEY_EDAMAM
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchByRecipeApiEdamam {
    @GET("/api/recipes/v2")
    suspend fun getRecipes(
        @Query("q")
        recipeOrIngredientQuery: String,
        @Query("diet")
        diet: String = "",
        @Query("cuisineType")
        cuisineType: String= "",
        @Query("mealType")
        mealType: String= "",
        @Query("dishType")
        dishType: String= "",
        @Query("calories")
        calories: String= "",
        @Query("time")
        time: String = "",
        @Query("glycemicIndex")
        glycemicIndex: String = "",
        @Query("excluded")
        excludedIngredients: String = "",

        @Query("type")
        type: String = "public",
        @Query("health")
        health: String = "vegan",
        @Query("app_id")
        appId: String = APP_ID_EDAMAM,
        @Query("app_key")
        appKey: String = APP_KEY_EDAMAM
    ): Response<EdamamSearchRecipeResponse>
}

//interface SearchByRecipeApiEdamamNotReady {
//    @GET("recipes/complexSearch")
//    suspend fun getRecipes(
//        @Query("query")
//        recipeOrIngredientQuery: String,
//        @Query("offset")
//        pagination: Int = 1,
//        @Query("cuisine")
//        cuisine: String = "",
//        @Query("excludeCuisine")
//        excludeCuisine: String = "",
//        @Query("diet")
//        veganDiet: String = "vegan,vegetarian",
//        @Query("intolerances")
//        intolerances: String = "",
//        @Query("equipment")
//        equipment: String = "",
//        @Query("type")
//        foodType: String = "",
//        @Query("instructionsRequired")
//        instructionsRequired: Boolean = true,
//        @Query("addRecipeInformation")
//        addRecipeInformation: Boolean = true,
//        @Query("addRecipeNutrition")
//        addRecipeNutrition: Boolean = true,
//        @Query("sort")
//        sort: String = "",
//        @Query("number")
//        numberOfResults: Int = 100,
//
//        @Query("apiKey")
//        apiKey: String = APP_KEY_SPOONACULAR
//
//    ): Response<SearchRecipeResponse>
//}