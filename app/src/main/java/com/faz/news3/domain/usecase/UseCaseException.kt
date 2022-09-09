package com.faz.domain.usecase

sealed class UseCaseException(cause: Throwable) : Throwable(cause) {
    class ArticleException(cause: Throwable) : UseCaseException(cause)
    class SourceException(cause: Throwable) : UseCaseException(cause)
    class UnknownException(cause: Throwable) : UseCaseException(cause)

    companion object {
        fun createFromThrowable(throwable: Throwable): UseCaseException {
            return if (throwable is UseCaseException)
                throwable else UnknownException(throwable)
        }
    }
}