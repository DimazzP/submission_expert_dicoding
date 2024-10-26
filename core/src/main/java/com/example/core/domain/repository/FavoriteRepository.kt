package com.example.core.domain.repository


import com.example.core.data.models.ResultUser
import com.example.core.domain.entity.Favorite
import kotlinx.coroutines.flow.Flow
interface FavoriteRepository {
    fun getFavoriteById(username: String): Flow<Favorite?>
    fun getFavorites(): Flow<List<Favorite>>
    suspend fun insert(favorite: Favorite): ResultUser<Unit>
    suspend fun delete(favorite: Favorite): ResultUser<Unit>
}