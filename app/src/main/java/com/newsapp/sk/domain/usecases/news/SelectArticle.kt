package com.newsapp.sk.domain.usecases.news

import com.newsapp.sk.data.local.NewsDao
import com.newsapp.sk.domain.model.Article
import com.newsapp.sk.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String):Article?{
        return newsRepository.selectArticle(url)
    }
}