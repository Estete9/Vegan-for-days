package com.epicusprogramming.veganfordays.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.epicusprogramming.veganfordays.R
import com.epicusprogramming.veganfordays.models.Recipe
import com.epicusprogramming.veganfordays.ui.RecipeViewModel
import com.epicusprogramming.veganfordays.ui.RecipesActivity
import com.klinker.android.link_builder.Link
import com.klinker.android.link_builder.LinkBuilder
import kotlinx.android.synthetic.main.fragment_recipe.*
import kotlinx.android.synthetic.main.fragment_recipe.view.*
import org.jsoup.Jsoup

class RecipeFragment : Fragment(R.layout.fragment_recipe) {

    lateinit var viewModel: RecipeViewModel
    val args: RecipeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as RecipesActivity).viewModel

        val recipe = args.recipe

        setContents(recipe)
        btScrollToIngredients.setOnClickListener { scrollToIngredients(tvIngredientsTitleFragment) }

    }

    private fun setContents(recipe: Recipe) {
        Glide.with(this).load(recipe.image).into(ivRecipeImageFragment)
//        tvCredits.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        tvCredits.text = recipe.creditsText
        val creditsLink =
            Link(recipe.creditsText).setTextColor(R.color.design_default_color_primary_variant)
                .setTextColorOfHighlightedLink(R.color.design_default_color_primary)
                .setUnderlined(true)
                .setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(recipe.sourceUrl)))
                }
//        tvCredits.applyLinks(creditsLink)
        LinkBuilder.on(tvCredits).addLink(creditsLink).build()

//        tvCredits.movementMethod = TouchableMovementMethod.instance


        tvRecipeTitleFragment.text = recipe.title
        tvTimeToPrepare.text = "Ready in ${recipe.readyInMinutes} minutes"
        tvRecipeSummaryFragment.text = Jsoup.parse(recipe.summary).text()
        tvIngredientsTitleFragment.text = "Ingredients"

        var ingredientsToString = ""
        recipe.nutrition.ingredients.forEach ingredientsLoop@{
            if (it == recipe.nutrition.ingredients.last()) {
                ingredientsToString += "${it.amount} ${it.unit} ${it.name}"
                return@ingredientsLoop
            }
            ingredientsToString += "${it.amount} ${it.unit} ${it.name}\n"

        }.also { tvIngredientsDetailFragment.text = ingredientsToString }

//        tvIngredientsDetailFragment.text = ingredientsToString
        tvInstructionsTitleFragment.text = "Instructions"

        var stepsToString = ""
        recipe.analyzedInstructions[0].steps.forEach stepsLoop@{
            if (it == recipe.analyzedInstructions[0].steps.last()) {
                stepsToString += "${it.number}) ${it.step}"
                return@stepsLoop
            }
            stepsToString += "${it.number}) ${it.step}\n\n"

        }.also { tvInstructionsDetailFragment.text = stepsToString }
//        tvInstructionsDetailFragment.text = stepsToString
    }

}

fun scrollToIngredients(textView: TextView) {

    textView.tvIngredientsTitleFragment.parent.requestChildFocus(
        textView.tvIngredientsTitleFragment,
        textView.tvIngredientsTitleFragment
    )
}