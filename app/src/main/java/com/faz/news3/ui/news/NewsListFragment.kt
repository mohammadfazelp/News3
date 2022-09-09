package com.faz.news3.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.faz.news3.R
import kotlinx.coroutines.launch

class NewsListFragment : Fragment(R.layout.fragment_news) {

    private val viewModel = ViewModelProvider(this)[NewsListViewModel::class.java]

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        with(viewModel) {
            loadNews(1)
            viewLifecycleOwner.lifecycleScope.launch {
                newsFlow.collect {
                    // display ui!
                }
            }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}