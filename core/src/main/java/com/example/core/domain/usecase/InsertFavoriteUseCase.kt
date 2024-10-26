package com.example.core.domain.usecase

import com.example.core.data.models.ResultUser
import com.example.core.domain.entity.Favorite
import com.example.core.domain.repository.FavoriteRepository
import javax.inject.Inject

class InsertFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend fun execute(favorite: Favorite): ResultUser<Unit> {
        return repository.insert(favorite)
    }
}