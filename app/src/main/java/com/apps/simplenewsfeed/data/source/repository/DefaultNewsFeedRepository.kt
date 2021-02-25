package com.apps.simplenewsfeed.data.source.repository

import com.apps.simplenewsfeed.data.models.Item
import com.apps.simplenewsfeed.data.source.local.LocalDataSource
import com.apps.simplenewsfeed.util.Result
import com.apps.simplenewsfeed.data.source.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultNewsFeedRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NewsFeedRepository {
    override suspend fun getItems(needsRefresh : Boolean): Result<List<Item>> = withContext(ioDispatcher) {
        if (needsRefresh) {
            when (val response = remoteDataSource.getNewsFeedItems()) {
                is Result.Success -> {
                    Result.Success(response.data)
                }

                is Result.Error -> {
                    Result.Error(response.exception)
                }

                else -> Result.Loading
            }
        } else {
            val items = localDataSource.getItems()
            Result.Success(items)
        }
    }

    override suspend fun saveItems(items: List<Item>) =
        withContext(ioDispatcher) {
            localDataSource.saveItems(items)
        }

    override suspend fun deleteItems() = withContext(ioDispatcher) {
        localDataSource.deleteItems()
    }
}