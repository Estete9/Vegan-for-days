package com.epicusprogramming.veganfordays.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.epicusprogramming.veganfordays.RecipeApplication
import com.epicusprogramming.veganfordays.models.SearchRecipeResponse
import com.epicusprogramming.veganfordays.repository.RecipeRepository
import com.epicusprogramming.veganfordays.util.Constants
import com.epicusprogramming.veganfordays.util.Resource
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response

class RecipeViewModel(
    app: Application,
    val recipeRepository: RecipeRepository
) : AndroidViewModel(app) {

    val searchRecipeLiveData: MutableLiveData<Resource<SearchRecipeResponse>> = MutableLiveData()
    var searchRecipesPage = 1
    var searchRecipesResponse: SearchRecipeResponse? = null

    var newSearchQuery: String? = null
    var oldSearchQuery: String? = null

    fun searchRecipe(recipeOrIngredientQuery: String) = viewModelScope.launch {
        safeSearchRecipeCall(recipeOrIngredientQuery)
    }

    //TODO error in pagination, it restarts the recyclerview and duplicates the search when it reaches the end of the list
    private fun handleSearchRecipeResponse(response: Response<SearchRecipeResponse>): Resource<SearchRecipeResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
//                if (searchRecipesResponse == null || newSearchQuery != oldSearchQuery) {
//                    searchRecipesPage = 1
//                    oldSearchQuery = newSearchQuery
//                    searchRecipesResponse = resultResponse
//                } else {
//                    searchRecipesPage += Constants.QUERY_PAGE_SIZE + 2
//                    val oldRecipes = searchRecipesResponse?.results
//                    val newRecipes = resultResponse.results
//
//                    oldRecipes?.addAll(newRecipes)
//                }
                return Resource.Success(searchRecipesResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private suspend fun safeSearchRecipeCall(recipeOrIngredientQuery: String) {
        newSearchQuery = recipeOrIngredientQuery
        searchRecipeLiveData.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response =
                    recipeRepository.searchRecipes(recipeOrIngredientQuery, searchRecipesPage)
                searchRecipeLiveData.postValue(handleSearchRecipeResponse(response))
            } else {
                searchRecipeLiveData.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> searchRecipeLiveData.postValue(Resource.Error("Network Failure"))
                else -> searchRecipeLiveData.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<RecipeApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false

                }
            }
        }
        return false
    }
}