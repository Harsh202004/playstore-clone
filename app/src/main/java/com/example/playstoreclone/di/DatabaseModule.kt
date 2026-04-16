package com.example.playstoreclone.di

import android.content.Context
import com.example.playstoreclone.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideAppDao(database: AppDatabase) = database.appDao()

    @Singleton
    @Provides
    fun provideReviewDao(database: AppDatabase) = database.reviewDao()
}

