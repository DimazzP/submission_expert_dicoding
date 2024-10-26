package com.example.favorite.feature.favorites

import FavoriteViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.domain.usecase.GetFavoritesUseCase

class FavoriteViewModelFactory(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(getFavoritesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
