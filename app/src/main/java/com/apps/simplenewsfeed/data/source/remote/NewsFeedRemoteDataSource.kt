package com.apps.simplenewsfeed.data.source.remote

import com.apps.simplenewsfeed.data.models.Item
import com.apps.simplenewsfeed.retrofit.ApiService
import com.apps.simplenewsfeed.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsFeedRemoteDataSource @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val retrofitClient: ApiService
) : RemoteDataSource {

    override suspend fun getNewsFeedItems(): Result<List<Item>> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = retrofitClient.getNewsFeedItems()
                if (result.isSuccessful) {
                    Result.Success(result.body()?.items)
                } else {
                    Result.Success(null)
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }
}