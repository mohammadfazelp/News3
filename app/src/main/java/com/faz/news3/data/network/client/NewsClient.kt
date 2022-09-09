package com.faz.news3.data.network.client

import com.faz.news3.data.model.NewsResponseApi
import com.faz.news3.data.network.service.NewsService
import javax.inject.Inject

class NewsClient @Inject constructor(private val service: NewsService) {

    suspend fun fetchNewsList(page: Int): NewsResponseApi =
        service.fetchNewsList(limit = PAGING_SIZE, offset = page * PAGING_SIZE)

    companion object {
        private const val PAGING_SIZE = 20
    }
}
