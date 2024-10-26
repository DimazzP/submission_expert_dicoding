package com.example.core.domain.usecase

import com.example.core.data.models.ResultUser
import com.example.core.domain.entity.Favorite
import com.example.core.domain.repository.FavoriteRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {

    // Menggunakan suspend untuk delete data secara asynchronous
    suspend fun execute(favorite: Favorite): ResultUser<Unit> {
        return repository.delete(favorite)
    }
}