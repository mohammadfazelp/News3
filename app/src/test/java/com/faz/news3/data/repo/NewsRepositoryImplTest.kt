package com.faz.news3.data.repo

import com.faz.news3.data.local.datasource.NewsLocalDataSource
import com.faz.news3.data.network.datasource.NewsRemoteDataSource
import com.faz.news3.domain.model.NewsArticle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class NewsRepositoryImplTest {

    private val remoteDataSource = mock<NewsRemoteDataSource>()
    private val localDataSource = mock<NewsLocalDataSource>()
    private val repoImpl = NewsRepositoryImpl(remoteDataSource, localDataSource)

    @ExperimentalCoroutinesApi
    @Test
    fun testFetchNews() = runTest {
        val news = listOf<NewsArticle>(
            NewsArticle(
                null, "David", null, "Something",
                "bla bla", null, "google.com", null
            )
        )
        whenever(remoteDataSource.fetchNews(1)).thenReturn(flowOf(news))
        val result = repoImpl.fetchNews(page = 1, false).first()
        Assert.assertEquals(news, result)
        verify(localDataSource).insertNews(news)
    }
}