package com.newsapp.sk.di

import android.app.Application
import com.newsapp.sk.data.LocalUserManagerImpl
import com.newsapp.sk.data.remote.dto.NewsApi
import com.newsapp.sk.data.repositoy.NewsRepositoryImpl
import com.newsapp.sk.domain.manager.LocalUserManager
import com.newsapp.sk.domain.repository.NewsRepository
import com.newsapp.sk.domain.usecases.app_entry.AppEntryUseCases
import com.newsapp.sk.domain.usecases.app_entry.ReadAppEntry
import com.newsapp.sk.domain.usecases.app_entry.SaveAppEntry
import com.newsapp.sk.domain.usecases.news.GetNews
import com.newsapp.sk.domain.usecases.news.NewsUseCases
import com.newsapp.sk.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi = newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository = newsRepository)
        )
    }
}