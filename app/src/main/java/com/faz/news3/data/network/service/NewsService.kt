package com.faz.news3.data.network.service

import com.faz.news3.data.model.NewsResponseApi
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET(NEWS_PATH)
    suspend fun fetchNewsList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): NewsResponseApi

    companion object {
        const val NEWS_PATH =
            "top-headlines?sources=techcrunch&apiKey=3944e2d5c7e34015856ca6c1c86122fa"
    }
}

