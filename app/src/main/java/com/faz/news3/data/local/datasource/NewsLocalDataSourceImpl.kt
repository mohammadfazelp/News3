package com.faz.news3.data.local.datasource

import com.faz.news3.data.mapper.mapFromEntityToPure
import com.faz.news3.data.mapper.mapFromPureToEntity
import com.faz.news3.data.local.dao.NewsDao
import com.faz.news3.domain.model.NewsArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsLocalDataSourceImpl(private val newsDao: NewsDao) : NewsLocalDataSource {
    override fun fetchNews(page: Int): Flow<List<NewsArticle>> = newsDao.getAllNews(page).map {
        it.mapFromEntityToPure()
    }

    override  fun insertNews(news: List<NewsArticle>) =
        newsDao.insertNews(news.mapFromPureToEntity())
}