package com.example.playstoreclone.data.repository

import com.example.playstoreclone.data.database.AppDao
import com.example.playstoreclone.data.database.ReviewDao
import com.example.playstoreclone.data.mock.MockData
import com.example.playstoreclone.data.model.AppModel
import com.example.playstoreclone.data.model.ReviewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val appDao: AppDao,
    private val reviewDao: ReviewDao
) {
    // App operations
    suspend fun loadMockData() {
        appDao.insertApps(MockData.mockApps)
        reviewDao.insertReviews(MockData.mockReviews)
    }

    fun getAllApps(): Flow<List<AppModel>> = appDao.getAllApps()

    fun getAppById(appId: String): Flow<AppModel?> = appDao.getAppById(appId)

    fun getAppsByCategory(category: String): Flow<List<AppModel>> =
        appDao.getAppsByCategory(category)

    fun searchApps(query: String): Flow<List<AppModel>> = appDao.searchApps(query)

    fun getTopRatedApps(): Flow<List<AppModel>> = appDao.getTopRatedApps()

    // Review operations
    fun getReviewsByAppId(appId: String): Flow<List<ReviewModel>> =
        reviewDao.getReviewsByAppId(appId)

    fun getAllReviews(): Flow<List<ReviewModel>> = reviewDao.getAllReviews()

    suspend fun addReview(review: ReviewModel) {
        reviewDao.insertReview(review)
    }

    suspend fun deleteReview(review: ReviewModel) {
        reviewDao.deleteReview(review)
    }
}

