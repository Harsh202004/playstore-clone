package com.example.playstoreclone.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "apps")
data class AppModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val developer: String,
    val description: String,
    @SerialName("short_description")
    val shortDescription: String,
    @SerialName("icon_url")
    val iconUrl: String,
    @SerialName("cover_url")
    val coverUrl: String,
    val category: String,
    val rating: Float,
    @SerialName("review_count")
    val reviewCount: Int,
    val installs: String,
    val price: Double,
    @SerialName("is_free")
    val isFree: Boolean,
    val size: String,
    @SerialName("release_date")
    val releaseDate: String,
    val version: String,
    @SerialName("last_update")
    val lastUpdate: String
)

