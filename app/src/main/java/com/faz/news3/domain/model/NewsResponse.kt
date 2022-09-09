package com.faz.news3.domain.model

import com.faz.news3.domain.model.NewsArticle

data class NewsResponse(
    val articles: List<NewsArticle>,
    var status: String,
    val totalResults: Int
)