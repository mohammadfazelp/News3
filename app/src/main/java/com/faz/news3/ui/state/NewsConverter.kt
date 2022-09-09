package com.faz.news3.ui.state

import com.faz.news3.domain.usecase.GetNewsUseCase
import com.faz.domain.usecase.Result
import com.faz.news3.domain.model.NewsArticle
import javax.inject.Inject

class NewsConverter @Inject constructor() {
    fun convert(result: Result<GetNewsUseCase.Response>): UiState<List<NewsArticle>> {
        return when (result) {
            is Result.Error -> {
                UiState.Error(result.useCaseException.localizedMessage.orEmpty())
            }
            is Result.Success -> {
                UiState.Success(
                    result.data.news
                )
            }
            else -> {
                UiState.Loading
            }
        }
    }
}