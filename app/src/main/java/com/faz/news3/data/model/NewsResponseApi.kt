package com.faz.news3.data.model

import com.faz.news3.data.model.NewsArticleApi
import com.squareup.moshi.Json

data class NewsResponseApi(
    @field:Json(name = "articles") val articles: List<NewsArticleApi>,
    @field:Json(name = "status") var status: String,
    @field:Json(name = "totalResults") val totalResults: Int
)