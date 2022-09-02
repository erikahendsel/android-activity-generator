package com.erikahendsel.activitygenerator.model

data class ActivityModel(
    val accessibility: Double,
    val activity: String,
    val key: String,
    val link: String,
    val participants: Int,
    val price: Double,
    val type: String
)