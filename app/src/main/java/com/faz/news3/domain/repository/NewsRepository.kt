package com.faz.news3.domain.repository

import com.faz.news3.domain.model.NewsArticle
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun fetchNews(page: Int): Flow<List<NewsArticle>>

    fun saveNews(news: List<NewsArticle>)
}