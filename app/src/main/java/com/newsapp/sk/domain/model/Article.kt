package com.newsapp.sk.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Article")
@Parcelize
data class Article(
    var author: String ,
    var content: String ,
    var description: String ,
    var publishedAt: String ,
    var source: Source ,
    var title: String ,
   @PrimaryKey var url: String ,
    var urlToImage: String
): Parcelable