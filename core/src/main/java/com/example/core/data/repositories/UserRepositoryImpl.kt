package com.example.core.data.repositories

import com.example.core.data.mappers.UserMapper
import com.example.core.data.models.SearchUserModel
import com.example.core.data.remote.datasource.UserRemoteDataSource
import com.example.core.domain.entity.DetailUser
import com.example.core.domain.entity.SearchUser
import com.example.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override fun getUserList(query: String): Flow<Result<List<SearchUser>>> {
        return userRemoteDataSource.getUserList(query)
            .map { result ->
                result.mapCatching { list ->
                    list.map { UserMapper.fromSearchUserModel(it) }
                }
            }
    }

    override fun getUserDetail(username: String): Flow<Result<DetailUser>> {
        return userRemoteDataSource.getUserDetail(username)
            .map { result ->
                result.mapCatching { detailModel ->
                    UserMapper.fromDetailUserModel(detailModel)
                }
            }
    }
}