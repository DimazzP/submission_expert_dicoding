//package com.example.core.di
//
//import com.example.core.domain.repository.FavoriteRepository
//import com.example.core.domain.usecase.DeleteFavoriteUseCase
//import com.example.core.domain.usecase.GetFavoritesUseCase
//import com.example.core.domain.usecase.InsertFavoriteUseCase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.EntryPoint
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object FavoriteUseCaseModule {
//
//    @Provides
//    @Singleton
//    fun provideGetFavoritesUseCase(
//        entryPoint: FavoriteEntryPoint
//    ): GetFavoritesUseCase {
//        return entryPoint.getGetFavoritesUseCase()
//    }
//
//    @Provides
//    @Singleton
//    fun provideInsertFavoriteUseCase(
//        entryPoint: FavoriteEntryPoint
//    ): InsertFavoriteUseCase {
//        return entryPoint.getInsertFavoriteUseCase()
//    }
//
//    @Provides
//    @Singleton
//    fun provideDeleteFavoriteUseCase(
//        entryPoint: FavoriteEntryPoint
//    ): DeleteFavoriteUseCase {
//        return entryPoint.getDeleteFavoriteUseCase()
//    }
//}
//
//@EntryPoint
//@InstallIn(SingletonComponent::class)
//interface FavoriteEntryPoint {
//    fun getFavoriteRepository(): FavoriteRepository
//    fun getGetFavoritesUseCase(): GetFavoritesUseCase
//    fun getInsertFavoriteUseCase(): InsertFavoriteUseCase
//    fun getDeleteFavoriteUseCase(): DeleteFavoriteUseCase
//}
