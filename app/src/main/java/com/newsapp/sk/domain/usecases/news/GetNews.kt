package com.newsapp.sk.domain.usecases.news

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.newsapp.sk.domain.model.Article
import com.newsapp.sk.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(source: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = source)
    }
}