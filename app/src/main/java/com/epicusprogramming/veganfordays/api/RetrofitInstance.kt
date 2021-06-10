package com.epicusprogramming.veganfordays.api

import com.epicusprogramming.veganfordays.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(logging).build()

            Retrofit.Builder()
                .baseUrl(Constants.SPOONACULAR_BASE_URL_COMPLEX)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api by lazy {
            retrofit.create(SearchByRecipeApi::class.java)
        }
    }
}