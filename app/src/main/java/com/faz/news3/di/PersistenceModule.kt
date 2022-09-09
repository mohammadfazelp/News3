package com.faz.news3.di

import android.app.Application
import androidx.room.Room
//import com.faz.news3.data.local.converter.TypeResponseConverter
import com.faz.news3.data.local.dao.NewsDao
import com.faz.news3.data.local.db.NewsDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

//    @Provides
//    @Singleton
//    fun provideMoshi(): Moshi {
//        return Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
//    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
//        , typeResponseConverter: TypeResponseConverter
    ): NewsDatabase {
        return Room.databaseBuilder(application, NewsDatabase::class.java, "news.db")
//            .fallbackToDestructiveMigration()
//            .addTypeConverter(typeResponseConverter)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(appDatabase: NewsDatabase): NewsDao {
        return appDatabase.newsDao()
    }

//    @Provides
//    @Singleton
//    fun provideTypeResponseConverter(moshi: Moshi): TypeResponseConverter {
//        return TypeResponseConverter(moshi)
//    }

//    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_preferences")
//    @Provides
//    fun provideAppDataStore(@ApplicationContext context:Context) = AppDataStore(context.dataStore)
}
