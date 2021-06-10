package com.epicusprogramming.veganfordays.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.epicusprogramming.veganfordays.repository.RecipeRepository

class RecipeViewModelProviderFactory(
    val app: Application,
    val recipeRepository: RecipeRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipeViewModel(app, recipeRepository) as T
    }
}