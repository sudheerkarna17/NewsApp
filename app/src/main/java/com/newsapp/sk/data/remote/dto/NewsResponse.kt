package com.newsapp.sk.data.remote.dto

import com.newsapp.sk.domain.model.Article

data class NewsResponse(
    var articles: List<Article> = listOf(),
    var status: String = "",
    var totalResults: Int = 0
)