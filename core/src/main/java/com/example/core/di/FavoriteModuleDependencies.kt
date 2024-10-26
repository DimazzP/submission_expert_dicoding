package com.example.core.di

import com.example.core.data.local.rooms.FavoriteDao
import com.example.core.domain.repository.FavoriteRepository
import com.example.core.domain.usecase.GetFavoritesUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun favoriteDao(): FavoriteDao
    fun favoriteRepository(): FavoriteRepository
    fun getFavoritesUseCase(): GetFavoritesUseCase
}
