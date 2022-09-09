package com.faz.domain.usecase

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val useCaseException: UseCaseException) : Result<Nothing>()
}