package com.newsapp.sk.data.repositoy

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.newsapp.sk.data.remote.dto.NewsApi
import com.newsapp.sk.data.remote.dto.NewsPagingSource
import com.newsapp.sk.data.remote.dto.SearchNewsPagingSource
import com.newsapp.sk.domain.model.Article
import com.newsapp.sk.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) :NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    source = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = searchQuery,
                    newsApi = newsApi,
                    source = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

}