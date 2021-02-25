package com.apps.simplenewsfeed.di

import com.apps.simplenewsfeed.data.source.local.LocalDataSource
import com.apps.simplenewsfeed.data.source.local.NewsLocalDataSource
import com.apps.simplenewsfeed.data.source.remote.NewsFeedRemoteDataSource
import com.apps.simplenewsfeed.data.source.remote.RemoteDataSource
import com.apps.simplenewsfeed.data.source.repository.DefaultNewsFeedRepository
import com.apps.simplenewsfeed.data.source.repository.NewsFeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {
    @Binds
    abstract fun providesRemoteDataSource(
        weatherRemoteDataSourceImpl: NewsFeedRemoteDataSource
    ): RemoteDataSource

    @Binds
    abstract fun providesRepository(
        repositoryImpl: DefaultNewsFeedRepository
    ): NewsFeedRepository

    @Binds
    abstract fun providesLocalDataSource(
        repositoryImpl: NewsLocalDataSource
    ): LocalDataSource
}