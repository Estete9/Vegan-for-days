package com.epicusprogramming.veganfordays.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epicusprogramming.veganfordays.R
import com.epicusprogramming.veganfordays.modelsEdamame.Hit
import kotlinx.android.synthetic.main.item_recipe_preview.view.*

val TAG = "RecipePreviewAdapter"


class RecipePreviewAdapter : RecyclerView.Adapter<RecipePreviewAdapter.RecipePreviewViewHolder>() {
    inner class RecipePreviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.recipe.url == newItem.recipe.url
        }

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }

    }


    val differ = AsyncListDiffer(this, differCallback)
//    var recipeList: MutableList<Recipe> = mutableListOf()

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
        val hit = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(hit.recipe.image).into(ivRecipeImagePreview)
            tvRecipeTitlePreview.text = hit.recipe.label
            tvTimeToMakePreview.text = "${hit.recipe.totalTime} minutes"

            tvSummaryPreview.text = hit.recipe.healthLabels.toString()

            setOnClickListener {
                onItemClickListener?.let { it(hit) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Hit) -> Unit)? = null

    fun setOnItemClickListener(listener: (Hit) -> Unit) {
        onItemClickListener = listener
    }
}