package com.faz.news3.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.faz.news3.R
import com.faz.news3.domain.model.NewsArticle
import com.faz.news3.ui.state.UiState
import kotlinx.coroutines.launch

class NewsListFragment : Fragment(R.layout.fragment_news) {

    private val viewModel = ViewModelProvider(this)[NewsListViewModel::class.java]

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
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

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun displayNewsOnUi(data: List<NewsArticle>) {
        // TODO
    }
}