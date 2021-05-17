package com.epicusprogramming.veganfordays.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.epicusprogramming.veganfordays.R
import kotlinx.android.synthetic.main.activity_recipes.*

class RecipesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        bottomNavigationView.setupWithNavController(recipeNavHostFragment.findNavController())
    }
}