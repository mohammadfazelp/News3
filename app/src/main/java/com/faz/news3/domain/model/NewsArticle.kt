package com.faz.news3.domain.model

data class NewsArticle(
   val publishedAt: String? = null,
   val author: String? = null,
   val source: NewsSource? = null,
   val title: String? = null,
   val description: String? = null,
   val url: String? = null,
   val urlToImage: String? = null,
   val content: String? = null
)