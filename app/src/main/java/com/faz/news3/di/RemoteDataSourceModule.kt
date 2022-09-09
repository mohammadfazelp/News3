package com.faz.news3.di

import com.faz.news3.data.network.datasource.NewsRemoteDataSource
import com.faz.news3.data.network.datasource.NewsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindNewsRemoteDataSource(newsRemoteDataSourceImpl: NewsRemoteDataSourceImpl):
            NewsRemoteDataSource
}