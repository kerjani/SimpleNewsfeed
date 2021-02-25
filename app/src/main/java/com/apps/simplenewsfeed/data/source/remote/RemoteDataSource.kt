package com.apps.simplenewsfeed.data.source.remote

import com.apps.simplenewsfeed.data.models.Item
import com.apps.simplenewsfeed.util.Result

interface RemoteDataSource {
    suspend fun getNewsFeedItems(): Result<List<Item>>
}
