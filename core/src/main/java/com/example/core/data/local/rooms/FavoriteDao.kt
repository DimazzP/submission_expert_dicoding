package com.example.core.data.local.rooms

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core.data.local.entities.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: FavoriteEntity)

    @Update
    suspend fun update(note: FavoriteEntity)

    @Delete
    suspend fun delete(note: FavoriteEntity)

    @Query("SELECT * from favoriteentity where username = :userlogin")
    fun getFavoriteById(userlogin: String): Flow<FavoriteEntity?>

    @Query("SELECT * from favoriteentity")
    fun getFavorites(): Flow<List<FavoriteEntity>>
}