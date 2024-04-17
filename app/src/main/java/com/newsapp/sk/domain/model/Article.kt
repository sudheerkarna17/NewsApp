package com.newsapp.sk.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(

    var author: String = "",
    var content: String = "",
    var description: String = "",
    var publishedAt: String = "",
    var source: Source = Source(),
    var title: String = "",
   @PrimaryKey var url: String = "",
    var urlToImage: String = ""
)