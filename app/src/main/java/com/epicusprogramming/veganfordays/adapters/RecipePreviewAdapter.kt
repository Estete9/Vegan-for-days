package com.epicusprogramming.veganfordays.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epicusprogramming.veganfordays.R
import com.epicusprogramming.veganfordays.models.Recipe
import kotlinx.android.synthetic.main.item_recipe_preview.view.*
import org.jsoup.Jsoup

class RecipePreviewAdapter : RecyclerView.Adapter<RecipePreviewAdapter.RecipePreviewViewHolder>() {
    inner class RecipePreviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.sourceUrl == newItem.sourceUrl
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipePreviewViewHolder {
        return RecipePreviewViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recipe_preview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipePreviewViewHolder, position: Int) {
        val recipe = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(recipe.image).into(ivRecipeImagePreview)
            tvRecipeTitlePreview.text = recipe.title
            tvTimeToMakeTVPreview.text = "${recipe.readyInMinutes} minutes"

            tvSummaryPreview.text = Jsoup.parse(recipe.summary).text()
//                recipe.summary/*"${recipe.summary.subSequence(0, 151)}..."*/

            setOnClickListener {
                onItemClickListener?.let { it(recipe) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Recipe) -> Unit)? = null

    fun setOnItemClickListener(listener: (Recipe) -> Unit) {
        onItemClickListener = listener
    }
}