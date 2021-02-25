package com.apps.simplenewsfeed.data.source.local

import com.apps.simplenewsfeed.data.models.Item
import com.apps.simplenewsfeed.data.source.local.dao.NewsDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(
    private val dao: NewsDao,
    private val ioDispatcher: CoroutineDispatcher
) : LocalDataSource {
    override suspend fun getItems(): List<Item> = withContext(ioDispatcher) {
        return@withContext dao.getItems()
    }

    override suspend fun saveItems(items: List<Item>) = withContext(ioDispatcher) {
        return@withContext dao.insertAll(items)
    }

    override suspend fun deleteItems() = withContext(ioDispatcher) {
        return@withContext dao.deleteAll()
    }
}