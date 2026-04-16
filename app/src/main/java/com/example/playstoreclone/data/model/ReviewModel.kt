package com.example.playstoreclone.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "reviews")
data class ReviewModel(
    @PrimaryKey
    val id: String,
    val appId: String,
    val userName: String,
    val rating: Float,
    val comment: String,
    val timestamp: Long
)

