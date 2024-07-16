package com.example.recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe.adapter.RecipeAdapter
import com.example.recipe.api.RecipeResponse
import com.example.recipe.api.RetrofitInstance
import com.example.recipe.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            Log.d("MainActivity", "Binding and layout inflation successful")

            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            adapter = RecipeAdapter(emptyList())
            binding.recyclerView.adapter = adapter
            Log.d("MainActivity", "RecyclerView and Adapter initialized")

            val apiKey = "38a13bb330854d7abeb77c8bb17d44e4"
            val query = "chicken"

            val service = RetrofitInstance.api
            val call = service.getRecipes(apiKey, query)
            Log.d("MainActivity", "API call initiated")

            call.enqueue(object : Callback<RecipeResponse> {
                override fun onResponse(
                    call: Call<RecipeResponse>,
                    response: Response<RecipeResponse>
                ) {
                    if (response.isSuccessful) {
                        val recipes = response.body()?.results
                        Log.d("MainActivity", "API response successful: $recipes")
                        recipes?.let {
                            adapter.updateRecipes(it)
                        }
                    } else {
                        Log.e("MainActivity", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                    Log.e("MainActivity", "Error: ${t.message}")
                }
            })
        } catch (e: Exception) {
            Log.e("MainActivity", "Exception in onCreate: ${e.message}")
        }
    }
}
