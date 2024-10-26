package com.example.core.data.remote.datasource

import com.example.core.data.models.DetailUserModel
import com.example.core.data.models.SearchUserModel
import com.example.core.data.remote.services.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    fun getUserList(query: String): Flow<Result<List<SearchUserModel>>> = flow {
        try {
            val response = apiService.getListUsers(query)
            if (response.isSuccessful) {
                val items = response.body()?.items ?: emptyList()
                emit(Result.success(items)) // Emit data yang diambil
            } else {
                emit(Result.failure(Exception("Error fetching users: ${response.errorBody()?.string()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e)) // Emit error jika ada exception
        }
    }

    fun getUserDetail(username: String): Flow<Result<DetailUserModel>> = flow {
        try {
            val response = apiService.getDetailUser(username)
            if (response.isSuccessful) {
                val detailUser = response.body()
                if (detailUser != null) {
                    emit(Result.success(detailUser)) // Emit data yang diambil
                } else {
                    emit(Result.failure(Exception("User not found")))
                }
            } else {
                emit(Result.failure(Exception("Error fetching user detail: ${response.errorBody()?.string()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}