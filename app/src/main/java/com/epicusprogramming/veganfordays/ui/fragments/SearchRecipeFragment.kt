package com.epicusprogramming.veganfordays.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epicusprogramming.veganfordays.R
import com.epicusprogramming.veganfordays.adapters.RecipePreviewAdapter
import com.epicusprogramming.veganfordays.ui.RecipeViewModel
import com.epicusprogramming.veganfordays.ui.RecipesActivity
import com.epicusprogramming.veganfordays.ui.hideKeyboard
import com.epicusprogramming.veganfordays.util.Constants.Companion.QUERY_PAGE_SIZE
import com.epicusprogramming.veganfordays.util.Constants.Companion.SEARCH_DELAY
import com.epicusprogramming.veganfordays.util.Resource
import kotlinx.android.synthetic.main.fragment_search_recipe.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchRecipeFragment : Fragment(R.layout.fragment_search_recipe) {
    lateinit var viewModel: RecipeViewModel
    lateinit var recipeAdapter: RecipePreviewAdapter

    val TAG = "SearchRecipeFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as RecipesActivity).viewModel
        setupRecyclerView()



        recipeAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("recipe", it)
            }
            findNavController().navigate(
                R.id.action_searchRecipeFragment_to_recipeFragment,
                bundle
            )
        }

        var job: Job? = null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchRecipe(editable.toString())
                    }
                }
            }
        }

        viewModel.searchRecipeLiveData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { recipeResponse ->
                        recipeAdapter.differ.submitList(recipeResponse.results.toList())
//                        val totalPages = recipeResponse.totalResults / QUERY_PAGE_SIZE + 2
//                        isLastPage = viewModel.searchRecipesPage == totalPages

                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occurred: $message")
                        Toast.makeText(activity, "An error occurred: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
//pagination
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val totalVisibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + totalVisibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate =
                isNotLoadingNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling
            if (shouldPaginate) {
                viewModel.searchRecipe(etSearch.text.toString())
                isScrolling = false
            }
        }
    }


    private fun setupRecyclerView() {
        recipeAdapter = RecipePreviewAdapter()
        rvSearchRecipe.apply {
            adapter = recipeAdapter
            layoutManager = LinearLayoutManager(activity)

            addOnScrollListener(this@SearchRecipeFragment.scrollListener)
        }
//        itemsInList.text = recipeAdapter.itemCount.toString()
    }
}