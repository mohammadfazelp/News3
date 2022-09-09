package com.faz.news3.data.model

import com.squareup.moshi.Json

data class NewsArticleApi(
    @field:Json(name = "publishedAt") val publishedAt: String? = null,
    @field:Json(name = "author") val author: String? = null,
    @field:Json(name = "source") val source: NewsSourceApi? = null,
    @field:Json(name = "title") val title: String? = null,
    @field:Json(name = "description") val description: String? = null,
    @field:Json(name = "url") val url: String? = null,
    @field:Json(name = "urlToImage") val urlToImage: String? = null,
    @field:Json(name = "content") val content: String? = null
)