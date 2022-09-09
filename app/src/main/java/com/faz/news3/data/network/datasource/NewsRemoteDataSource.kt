package com.faz.news3.data.network.datasource

import com.faz.news3.domain.model.NewsArticle
import kotlinx.coroutines.flow.Flow

interface NewsRemoteDataSource {
    fun fetchNews(page: Int): Flow<List<NewsArticle>>
}