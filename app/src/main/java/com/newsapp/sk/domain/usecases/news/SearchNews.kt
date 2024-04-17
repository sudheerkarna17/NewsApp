package com.newsapp.sk.domain.usecases.news

import androidx.paging.PagingData
import com.newsapp.sk.domain.model.Article
import com.newsapp.sk.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(searchQuery: String, source: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery = searchQuery, sources = source)
    }
}