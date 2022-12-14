package com.faz.news3.data.repo

import com.faz.news3.data.local.datasource.NewsLocalDataSource
import com.faz.news3.data.network.datasource.NewsRemoteDataSource
import com.faz.news3.domain.model.NewsArticle
import com.faz.news3.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {

    override suspend fun fetchNews(page: Int, offline: Boolean): Flow<List<NewsArticle>> =
        if (offline) {
            newsLocalDataSource.fetchNews(page)
        } else {
            newsRemoteDataSource.fetchNews(page).onEach {
                newsLocalDataSource.insertNews(it)
            }
        }

//    override suspend fun fetchNews(page: Int): Flow<List<NewsArticle>> {
//        val news = newsLocalDataSource.fetchNews(page)
//        news.onEmpty {
//            newsRemoteDataSource.fetchNews(page).onEach {
//                newsLocalDataSource.insertNews(it)
//            }
//        }
//        return newsLocalDataSource.fetchNews(page)
//    }

    override fun saveNews(news: List<NewsArticle>) {
        newsLocalDataSource.insertNews(news)
    }
}