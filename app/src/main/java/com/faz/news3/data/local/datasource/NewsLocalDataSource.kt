package com.faz.news3.data.local.datasource

import com.faz.news3.domain.model.NewsArticle
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    fun fetchNews(page: Int): Flow<List<NewsArticle>>
    fun insertNews(news: List<NewsArticle>)
}