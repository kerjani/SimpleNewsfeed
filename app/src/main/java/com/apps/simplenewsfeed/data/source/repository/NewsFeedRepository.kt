package com.apps.simplenewsfeed.data.source.repository

import com.apps.simplenewsfeed.data.models.Item
import com.apps.simplenewsfeed.util.Result

interface NewsFeedRepository {
    suspend fun getItems(needsRefresh : Boolean) : Result<List<Item>>
    suspend fun saveItems(items: List<Item>)
    suspend fun deleteItems()
}