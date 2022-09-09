package com.faz.news3.domain.usecase

import com.faz.domain.usecase.UseCaseException
import com.faz.news3.domain.Configuration
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

class UseCaseTest {

    @ExperimentalCoroutinesApi
    private lateinit var useCase: UseCase<UseCase.Request, UseCase.Response>
    private val request = mock<UseCase.Request>()
    private val response = mock<UseCase.Response>()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        useCase =
            object : UseCase<UseCase.Request, UseCase.Response>(Configuration(Dispatchers.IO)) {
                override suspend fun process(request: Request): Flow<Response> {
                    assertEquals(this@UseCaseTest.request, request)
                    return flowOf(response)
                }
            }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testExecuteSuccess() = runTest {
        val result = useCase.execute(request).first()
        assertEquals(result, Result.success(response))
    }


    @ExperimentalCoroutinesApi
    @Test
    fun testExecuteNewsArticleException() {
        useCase =
            object : UseCase<UseCase.Request, UseCase.Response>(Configuration(Dispatchers.IO)) {
                override suspend fun process(request: Request): Flow<Response> {
                    assertEquals(this@UseCaseTest.request, request)
                    return flow {
                        throw UseCaseException.createFromThrowable(Throwable())
                    }
                }

            }
        runTest {
            val result = useCase.execute(request).first()
            assertTrue((result as com.faz.domain.usecase.Result.Error).useCaseException is UseCaseException.ArticleException)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testExecuteUnknownException() {
        useCase =
            object : UseCase<UseCase.Request, UseCase.Response>(Configuration(Dispatchers.IO)) {
                override suspend fun process(request: Request): Flow<Response> {
                    assertEquals(this@UseCaseTest.request, request)
                    return flow {
                        throw Throwable()
                    }
                }

            }
        runTest {
            val result = useCase.execute(request).first()
            assertTrue((result as com.faz.domain.usecase.Result.Error).useCaseException is UseCaseException.UnknownException)
        }
    }
}