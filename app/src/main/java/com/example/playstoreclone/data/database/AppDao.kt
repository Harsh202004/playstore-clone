package com.example.playstoreclone.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.playstoreclone.data.model.AppModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApp(app: AppModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApps(apps: List<AppModel>)

    @Delete
    suspend fun deleteApp(app: AppModel)

    @Query("SELECT * FROM apps")
    fun getAllApps(): Flow<List<AppModel>>

    @Query("SELECT * FROM apps WHERE id = :appId")
    fun getAppById(appId: String): Flow<AppModel?>

    @Query("SELECT * FROM apps WHERE category = :category")
    fun getAppsByCategory(category: String): Flow<List<AppModel>>

    @Query("SELECT * FROM apps WHERE name LIKE '%' || :query || '%' OR developer LIKE '%' || :query || '%'")
    fun searchApps(query: String): Flow<List<AppModel>>

    @Query("SELECT * FROM apps ORDER BY rating DESC")
    fun getTopRatedApps(): Flow<List<AppModel>>
}

