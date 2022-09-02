package com.erikahendsel.activitygenerator.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api:BoredApi by lazy {
        retrofit.create(BoredApi::class.java)
    }
}