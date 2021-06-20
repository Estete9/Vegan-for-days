package com.epicusprogramming.veganfordays.util

import com.epicusprogramming.veganfordays.BuildConfig


class Constants {
    companion object {
        const val APP_KEY_SPOONACULAR = BuildConfig.SPOONACULAR_API_KEY
        const val SPOONACULAR_BASE_URL_COMPLEX = "https://api.spoonacular.com"
        const val APP_KEY_EDAMAM = BuildConfig.EDAMAM_API_KEY
        const val APP_ID_EDAMAM = BuildConfig.EDAMAM_APP_ID
        const val EDAMAM_BASE_URL = "https://api.edamam.com/"

        const val SEARCH_DELAY = 500L
        const val QUERY_PAGE_SIZE = 20
    }
}