package com.newsapp.sk.domain.usecases.news

import com.newsapp.sk.data.local.NewsDao
import com.newsapp.sk.domain.model.Article
import com.newsapp.sk.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}