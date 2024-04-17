package com.newsapp.sk.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.newsapp.sk.domain.model.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDataBase : RoomDatabase() {

    abstract val newsDao: NewsDao
}