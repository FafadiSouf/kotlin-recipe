package com.example.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipe.R
import com.example.recipe.databinding.ItemRecipeBinding
import com.example.recipe.model.Recipe

class RecipeAdapter(private var recipes: List<Recipe>) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun updateRecipes(updatedRecipes: List<Recipe>) {
        recipes = updatedRecipes
        notifyDataSetChanged()
    }

    inner class RecipeViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            binding.recipeTitle.text = recipe.title
            Glide.with(binding.recipeImage.context)
                .load(recipe.image)
                .placeholder(R.drawable.placeholders_image)
                .into(binding.recipeImage)
            binding.recipeIngredients.text = recipe.ingredients.joinToString(separator = "\n") { "- $it" }
        }
    }
}
