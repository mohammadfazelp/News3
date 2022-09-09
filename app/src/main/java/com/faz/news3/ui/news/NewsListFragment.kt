package com.faz.news3.ui.news

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faz.news3.R
import com.faz.news3.domain.model.NewsArticle
import com.faz.news3.ui.adapter.NewsAdapter
import com.faz.news3.ui.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsListFragment : Fragment(R.layout.fragment_news) {

    private val viewModel: NewsListViewModel by viewModels()

    private lateinit var progressBar: ProgressBar
    private lateinit var emptyTextView: TextView
    private lateinit var recyclerview: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressBar = view.findViewById(R.id.progressBar)
        recyclerview = view.findViewById(R.id.recyclerview)
        emptyTextView = view.findViewById(R.id.emptyTextView)
        with(viewModel) {
            loadNews(1)
            viewLifecycleOwner.lifecycleScope.launch {
                newsFlow.collect { uiState ->
                    when (uiState) {
                        is UiState.Success -> updateUi(uiState.data)
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

        emptyTextView.visibility = View.VISIBLE
        recyclerview.visibility = View.INVISIBLE
        progressBar.visibility = View.INVISIBLE

        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun updateUi(news: List<NewsArticle>) {

        emptyTextView.visibility = View.INVISIBLE
        progressBar.visibility = View.INVISIBLE
        recyclerview.visibility = View.VISIBLE

        recyclerview.layoutManager = LinearLayoutManager(context)
        val adapter = NewsAdapter(requireContext(), news)
        recyclerview.adapter = adapter
    }
}