package com.newsapp.sk.presentation.bookmark

import com.newsapp.sk.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
