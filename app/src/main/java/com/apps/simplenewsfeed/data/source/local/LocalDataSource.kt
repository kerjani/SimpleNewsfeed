package com.apps.simplenewsfeed.data.source.local

import com.apps.simplenewsfeed.data.models.Item

interface LocalDataSource {

    suspend fun getItems(): List<Item>

    suspend fun saveItems(weatherData: List<Item>)

    suspend fun deleteItems()
}