package com.epicusprogramming.veganfordays.util

import com.epicusprogramming.veganfordays.BuildConfig


class Constants {
    companion object {
        const val APP_KEY_SPOONACULAR = BuildConfig.SPOONACULAR_API_KEY
        const val BASE_URL_COMPLEX = "https://api.spoonacular.com"
        const val SEARCH_DELAY = 500L
        const val QUERY_PAGE_SIZE = 20
//        const val APP_KEY_EDAMAM = BuildConfig.EDAMAM_API_KEY
    }
}