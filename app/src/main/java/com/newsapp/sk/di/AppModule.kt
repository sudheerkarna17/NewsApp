package com.newsapp.sk.di

import android.app.Application
import androidx.room.Room
import com.newsapp.sk.data.LocalUserManagerImpl
import com.newsapp.sk.data.local.NewsDao
import com.newsapp.sk.data.local.NewsDataBase
import com.newsapp.sk.data.local.NewsTypeConverter
import com.newsapp.sk.data.remote.dto.NewsApi
import com.newsapp.sk.data.repositoy.NewsRepositoryImpl
import com.newsapp.sk.domain.manager.LocalUserManager
import com.newsapp.sk.domain.repository.NewsRepository
import com.newsapp.sk.domain.usecases.app_entry.AppEntryUseCases
import com.newsapp.sk.domain.usecases.app_entry.ReadAppEntry
import com.newsapp.sk.domain.usecases.app_entry.SaveAppEntry
import com.newsapp.sk.domain.usecases.news.DeleteArticle
import com.newsapp.sk.domain.usecases.news.GetNews
import com.newsapp.sk.domain.usecases.news.NewsUseCases
import com.newsapp.sk.domain.usecases.news.SearchNews
import com.newsapp.sk.domain.usecases.news.SelectArticles
import com.newsapp.sk.domain.usecases.news.UpsertArticle
import com.newsapp.sk.util.Constants
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
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository = newsRepository),
            searchNews = SearchNews(newsRepository = newsRepository),
            upsertArticle = UpsertArticle(newsDao = newsDao),
            deleteArticle = DeleteArticle(newsDao = newsDao),
            selectArticles = SelectArticles(newsDao = newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDataBase(
        application: Application
    ): NewsDataBase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDataBase::class.java,
            name = Constants.NEWS_DATA_BASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDataBase: NewsDataBase
    ): NewsDao = newsDataBase.newsDao
}