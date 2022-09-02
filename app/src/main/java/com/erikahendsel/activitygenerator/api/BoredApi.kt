package com.erikahendsel.activitygenerator.api

import com.erikahendsel.activitygenerator.model.ActivityModel
import retrofit2.Response
import retrofit2.http.GET

interface BoredApi {

    @GET("activity")
    suspend fun getActivity(): Response<ActivityModel>
}