package com.newsapp.sk.di

import android.app.Application
import com.newsapp.sk.data.LocalUserManagerImpl
import com.newsapp.sk.domain.manager.LocalUserManager
import com.newsapp.sk.domain.usecases.AppEntryUseCases
import com.newsapp.sk.domain.usecases.ReadAppEntry
import com.newsapp.sk.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

}