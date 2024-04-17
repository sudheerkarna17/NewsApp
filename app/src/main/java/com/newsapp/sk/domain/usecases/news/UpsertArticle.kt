package com.newsapp.sk.domain.usecases.news

import com.newsapp.sk.data.local.NewsDao
import com.newsapp.sk.domain.model.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.upsert(article)
    }
}