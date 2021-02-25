package com.apps.simplenewsfeed.retrofit

import com.apps.simplenewsfeed.data.models.Items
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val BASE_API_URL = "https://mb.appslab.services/"
    }

    @GET("hw/apps_test_v1.json")
    suspend fun getNewsFeedItems(): Response<Items>
}