package com.example.core.data.local.datasources

import com.example.core.data.local.entities.FavoriteEntity
import com.example.core.data.local.rooms.FavoriteDao
import kotlinx.coroutines.flow.Flow

class FavoriteLocalDataSource(private val favoriteDao: FavoriteDao) {
    fun getFavoriteById(username: String): Flow<FavoriteEntity?> = favoriteDao.getFavoriteById(username)

    fun getFavorites(): Flow<List<FavoriteEntity>> = favoriteDao.getFavorites()

    suspend fun insert(favorite: FavoriteEntity) {
        favoriteDao.insert(favorite)
    }

    suspend fun delete(favorite: FavoriteEntity) {
        favoriteDao.delete(favorite)
    }
}