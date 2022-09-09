package com.faz.news3.domain.usecase

import com.faz.news3.domain.Configuration
import com.faz.news3.domain.model.NewsArticle
import com.faz.news3.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
//    configuration: Configuration,
    private val newsRepository: NewsRepository
) : UseCase<GetNewsUseCase.Request, GetNewsUseCase.Response>(Configuration(Dispatchers.IO)) {

    data class Request(val page: Int, val isOffline: Boolean) : UseCase.Request

    data class Response(val news: List<NewsArticle>) : UseCase.Response

    override suspend fun process(request: Request): Flow<Response> =
        newsRepository.fetchNews(page = request.page, request.isOffline).map {
            Response(it)
        }
}