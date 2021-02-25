package com.apps.simplenewsfeed.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apps.simplenewsfeed.data.models.Item

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(weatherData: List<Item>)

    @Query("SELECT * FROM items")
    suspend fun getItems(): List<Item>

    @Query("DELETE FROM items")
    suspend fun deleteAll()
}