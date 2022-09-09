package com.faz.news3.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.faz.news3.data.local.entity.NewsEntity
//import com.faz.news3.data.local.converter.TypeResponseConverter
import com.faz.news3.data.local.dao.NewsDao

@Database(
    entities = [NewsEntity::class],
    version = 1,
    exportSchema = true
)
//@TypeConverters(value = [TypeResponseConverter::class])
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}