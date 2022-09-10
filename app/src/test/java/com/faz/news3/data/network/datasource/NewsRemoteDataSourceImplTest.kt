package com.faz.news3.data.network.datasource

import com.faz.domain.usecase.UseCaseException
import com.faz.news3.data.model.NewsArticleApi
import com.faz.news3.data.model.NewsResponseApi
import com.faz.news3.data.network.service.NewsService
import com.faz.news3.domain.model.NewsArticle
import com.faz.news3.domain.model.NewsResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class NewsRemoteDataSourceImplTest {

    private val newsService = mock<NewsService>()
    private val newsRemoteDataSource = NewsRemoteDataSourceImpl(newsService)

    @ExperimentalCoroutinesApi
    @Test
    fun testFetchNews() = runTest {
        val remoteNews = NewsResponseApi(
            listOf(
                NewsArticleApi(
                    "null", "Kambiz", null, "Title...",
                    "Description", "google.com", null, null
                )
            ), "200", 10
        )

        val exceptedNews = NewsResponse(
            listOf(
                NewsArticle(
                    "null", "Kambiz", null, "Title...",
                    "Description", "google.com", null, null
                )
            ), "200", 10
        )
        whenever(newsService.fetchNewsList()).thenReturn(remoteNews)
        val page = 1
        val result = newsRemoteDataSource.fetchNews(page).first()
        Assert.assertEquals(exceptedNews, result)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun testFetchNewsThrowsError() = runTest {
        whenever(newsService.fetchNewsList()).thenThrow(RuntimeException())
        newsRemoteDataSource.fetchNews(page = 1).catch {
            Assert.assertTrue(it is UseCaseException.ArticleException)
        }.collect {
            // todo
        }
    }
}