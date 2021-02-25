package com.apps.simplenewsfeed.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.apps.simplenewsfeed.data.models.Item
import com.apps.simplenewsfeed.data.source.local.dao.NewsDao

@Database(entities = [Item::class], version = 2, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {

        @Volatile
        private var instance: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, NewsDatabase::class.java, "characters")
                .fallbackToDestructiveMigration()
                .build()
    }
}