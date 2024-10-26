package com.example.core.data.repositories
import android.util.Log
import com.example.core.data.local.datasources.FavoriteLocalDataSource
import com.example.core.data.models.ResultUser
import com.example.core.domain.entity.Favorite
import com.example.core.domain.repository.FavoriteRepository
import com.example.core.data.mappers.toDomainModel
import com.example.core.data.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class FavoriteRepositoryImpl(
    private val localDataSource: FavoriteLocalDataSource
) : FavoriteRepository {

    override fun getFavoriteById(username: String): Flow<Favorite?> {
        return localDataSource.getFavoriteById(username).map { entity ->
            entity?.toDomainModel()
        }
    }

    override fun getFavorites(): Flow<List<Favorite>> {
        return flow {
            try {
                // Log panggilan fungsi
                Log.d("testingcoba", "Data yang diterima dari localDataSource: terpanggil")

                // Emit data dari localDataSource
                localDataSource.getFavorites().collect { entities ->
                    Log.d("testingcoba", "Data yang diterima dari localDataSource: $entities")

                    // Emit hasil setelah konversi ke domain model
                    val favorites = entities.map { it.toDomainModel() }
                    emit(favorites)

                    Log.d("testingcoba", "Data setelah konversi ke domain model: $favorites")
                }
            } catch (e: Exception) {
                // Log error jika terjadi exception
                Log.e("testingcoba", "Error saat mengambil data dari localDataSource", e)
                // Emit empty list jika ada error untuk memastikan Flow tetap berjalan
                emit(emptyList<Favorite>())
            }
        }
    }

    override suspend fun insert(favorite: Favorite): ResultUser<Unit> {
        return try {
            localDataSource.insert(favorite.toEntity())
            ResultUser.Success(Unit)
        } catch (e: Exception) {
            ResultUser.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun delete(favorite: Favorite): ResultUser<Unit> {
        return try {
            localDataSource.delete(favorite.toEntity())  // Memanggil mapper di sini
            ResultUser.Success(Unit)
        } catch (e: Exception) {
            ResultUser.Error(e.message ?: "An error occurred")
        }
    }
}