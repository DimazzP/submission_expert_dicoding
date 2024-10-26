package com.example.core.domain.usecase

import com.example.core.domain.entity.Favorite
import com.example.core.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    fun execute(): Flow<List<Favorite>> {
        return repository.getFavorites()
    }
}