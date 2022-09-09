package com.faz.news3.ui.news

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.faz.news3.R
import com.faz.news3.domain.model.NewsArticle
import com.faz.news3.ui.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsListFragment : Fragment(R.layout.fragment_news) {

    private val viewModel: NewsListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(viewModel) {
            loadNews(1)
            viewLifecycleOwner.lifecycleScope.launch {
                newsFlow.collect { uiState ->
                    when (uiState) {
                        is UiState.Success -> displayNewsOnUi(uiState.data)
                        is UiState.Error -> showError(uiState.errorMessage)
                        else -> {
                            Unit
                        }
                    }
                }
            }
        }
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun displayNewsOnUi(data: List<NewsArticle>) {
        println()
        // TODO
    }
}