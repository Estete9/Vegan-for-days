package com.epicusprogramming.veganfordays.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.epicusprogramming.veganfordays.R
import com.epicusprogramming.veganfordays.ui.RecipeViewModel
import com.epicusprogramming.veganfordays.ui.RecipesActivity
import kotlinx.android.synthetic.main.fragment_web_view_recipe.*

class WebViewRecipeFragment : Fragment(R.layout.fragment_web_view_recipe) {

    lateinit var viewModel: RecipeViewModel
    val args: RecipeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as RecipesActivity).viewModel
        val recipe = args.recipe
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(recipe.sourceUrl)
        }
    }
}