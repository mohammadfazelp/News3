package com.faz.news3.domain.usecase

import com.faz.news3.domain.model.NewsArticle
import com.faz.news3.domain.repository.NewsRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetNewsUseCaseTest {

    private val repository = mock<NewsRepository>()
    private val useCase = GetNewsUseCase(repository)


    @ExperimentalCoroutinesApi
    @Test
    fun testProcess() = runTest {
        val request = GetNewsUseCase.Request(1, false)
        val news = listOf(
            NewsArticle(
                null, "Ali", null, "Title",
                "Desc", null, "https://google.com", ""
            )
        )
        whenever(repository.fetchNews(request.page, request.isOffline)).thenReturn(flowOf(news))
        val response = useCase.process(request).first()
        assertEquals(GetNewsUseCase.Response(news), response)
    }
}