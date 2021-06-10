package com.epicusprogramming.veganfordays.util

import com.epicusprogramming.veganfordays.BuildConfig


class Constants {
    companion object {
        const val SPOONACULAR_APP_KEY = BuildConfig.SPOONACULAR_API_KEY
        const val SPOONACULAR_BASE_URL_COMPLEX = "https://api.spoonacular.com"
        const val EDAMAM_APP_KEY = BuildConfig.EDAMAM_API_KEY
        const val EDAMAM_BASE_URL = ""
        const val SEARCH_DELAY = 500L
        const val QUERY_PAGE_SIZE = 20
//        /recipes/complexSearch/
    }
}