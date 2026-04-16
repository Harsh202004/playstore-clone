package com.example.playstoreclone.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.playstoreclone.data.model.ReviewModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(review: ReviewModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(reviews: List<ReviewModel>)

    @Delete
    suspend fun deleteReview(review: ReviewModel)

    @Query("SELECT * FROM reviews WHERE appId = :appId ORDER BY timestamp DESC")
    fun getReviewsByAppId(appId: String): Flow<List<ReviewModel>>

    @Query("SELECT * FROM reviews ORDER BY timestamp DESC")
    fun getAllReviews(): Flow<List<ReviewModel>>
}

