package com.example.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.core.data.local.databases.FavoriteRoomDatabase
import com.example.core.data.local.datasources.FavoriteLocalDataSource
import com.example.core.data.local.datastore.SettingPreferences
import com.example.core.data.local.datastore.dataStore
import com.example.core.data.local.rooms.FavoriteDao
import com.example.core.data.remote.datasource.UserRemoteDataSource
import com.example.core.data.remote.services.ApiConfig
import com.example.core.data.remote.services.ApiService
import com.example.core.data.repositories.FavoriteRepositoryImpl
import com.example.core.data.repositories.UserRepositoryImpl
import com.example.core.domain.repository.FavoriteRepository
import com.example.core.domain.repository.UserRepository
import com.example.core.domain.usecase.DeleteFavoriteUseCase
import com.example.core.domain.usecase.GetFavoriteByIdUseCase
import com.example.core.domain.usecase.GetFavoritesUseCase
import com.example.core.domain.usecase.GetUserDetailUseCase
import com.example.core.domain.usecase.GetUserListUseCase
import com.example.core.domain.usecase.InsertFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideApiService(): ApiService {
//        return ApiConfig.getApiService()
//    }

    @Provides
    @Singleton
    fun provideApiService(apiConfig: ApiConfig): ApiService {
        return apiConfig.getApiService()
    }

    @Provides
    @Singleton
    fun provideApiConfig(): ApiConfig {
        return ApiConfig()
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(apiService: ApiService): UserRemoteDataSource {
        return UserRemoteDataSource(apiService)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userRemoteDataSource: UserRemoteDataSource): UserRepository {
        return UserRepositoryImpl(userRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetUserListUseCase(userRepository: UserRepository): GetUserListUseCase {
        return GetUserListUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideGetUserDetailUseCase(userRepository: UserRepository): GetUserDetailUseCase {
        return GetUserDetailUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("settings") }
        )
    }

    @Provides
    @Singleton
    fun provideSettingPreferences(dataStore: DataStore<Preferences>): SettingPreferences {
        return SettingPreferences.getInstance(dataStore)
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideFavoriteRoomDatabase(
        @ApplicationContext context: Context
    ): FavoriteRoomDatabase {
        return Room.databaseBuilder(
            context,
            FavoriteRoomDatabase::class.java,
            "favorite_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(db: FavoriteRoomDatabase): FavoriteDao {
        return db.favoriteDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteLocalDataSource(
        dao: FavoriteDao
    ): FavoriteLocalDataSource {
        return FavoriteLocalDataSource(dao)
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        localDataSource: FavoriteLocalDataSource
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(localDataSource)
    }

    @Provides
    fun provideGetFavoritesUseCase(repository: FavoriteRepository): GetFavoritesUseCase {
        return GetFavoritesUseCase(repository)
    }

    @Provides
    fun provideGetFavoritesByIdUseCase(repository: FavoriteRepository): GetFavoriteByIdUseCase {
        return GetFavoriteByIdUseCase(repository)
    }

    @Provides
    fun provideInsertFavoritesUseCase(repository: FavoriteRepository): InsertFavoriteUseCase {
        return InsertFavoriteUseCase(repository)
    }

    @Provides
    fun provideDeleteFavoritesUseCase(repository: FavoriteRepository): DeleteFavoriteUseCase {
        return DeleteFavoriteUseCase(repository)
    }
}
