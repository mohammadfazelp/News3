package com.faz.news3.di

import com.faz.news3.data.network.datasource.NewsRemoteDataSource
import com.faz.news3.data.repo.NewsRepositoryImpl
import com.faz.news3.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideNewsRepository(
        remoteDataSource: NewsRemoteDataSource
//        , localDataSource: NewsLocalDataSource
    ): NewsRepository = NewsRepositoryImpl(
        remoteDataSource
//        , localDataSource
    )
}