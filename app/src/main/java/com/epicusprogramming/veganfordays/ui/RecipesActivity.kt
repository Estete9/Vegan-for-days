package com.epicusprogramming.veganfordays.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.epicusprogramming.veganfordays.R
import com.epicusprogramming.veganfordays.db.RecipeDatabase
import com.epicusprogramming.veganfordays.repository.RecipeRepository
import kotlinx.android.synthetic.main.activity_recipes.*

class RecipesActivity : AppCompatActivity() {

    lateinit var viewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        val recipeRepository = RecipeRepository(RecipeDatabase(this))
        val recipeProviderFactory = RecipeViewModelProviderFactory(application, recipeRepository)
        viewModel = ViewModelProvider(this, recipeProviderFactory).get(RecipeViewModel::class.java)

        hideKeyboard()

        bottomNavigationView.setupWithNavController(recipeNavHostFragment.findNavController())

    }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}