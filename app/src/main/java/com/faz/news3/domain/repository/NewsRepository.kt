package com.faz.news3.domain.repository

import com.faz.news3.domain.model.NewsArticle
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun fetchNews(page: Int, offline: Boolean): Flow<List<NewsArticle>>
    fun saveNews(news: List<NewsArticle>)
}