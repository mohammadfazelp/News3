package com.faz.news3.domain.model

data class NewsResponse(
    val articles: List<NewsArticle>,
    var status: String,
    val totalResults: Int
)