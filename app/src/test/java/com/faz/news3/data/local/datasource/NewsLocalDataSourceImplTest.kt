package com.faz.news3.data.local.datasource

import com.faz.news3.data.local.dao.NewsDao
import com.faz.news3.data.local.entity.NewsEntity
import com.faz.news3.data.mapper.mapFromEntityToPure
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

class NewsLocalDataSourceImplTest {

    private val newsDao = mock<NewsDao>()
    private val newsLocalDataSource = NewsLocalDataSourceImpl(newsDao)

    @ExperimentalCoroutinesApi
    @Test
    fun testFetchNews() = runTest {
        val localNews = listOf<NewsEntity>(
            NewsEntity(
                1, null, "Mohammad",
                "My Title", "Description", null, "yahoo.com", null
            )
        )
        val exceptedNews = listOf(
            NewsArticle(
                null, "Mohammad", null,
                "My Title", "Description", null, "yahoo.com", null
            )
        )

        whenever(newsDao.fetchNews()).thenReturn(flowOf(localNews))
        val page = 1
        val result = newsLocalDataSource.fetchNews(page).first()
        Assert.assertEquals(exceptedNews, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testAddNews() = runTest {
        val localNews = listOf(
            NewsEntity(
                1, null, "Mohammad",
                "My Title", "Description", null, "yahoo.com", null
            )
        )

        val news = listOf(
            NewsArticle(
                null, "Mohammad", null,
                "My Title", "Description", null, "yahoo.com", null
            )
        )
        newsLocalDataSource.insertNews(news)
        verify(newsDao).insertNews(localNews)
    }
}