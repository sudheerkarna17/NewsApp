package com.newsapp.sk.domain.usecases.news

import com.newsapp.sk.data.local.NewsDao
import com.newsapp.sk.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}