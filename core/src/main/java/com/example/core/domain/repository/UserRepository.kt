package com.example.core.domain.repository

import com.example.core.domain.entity.DetailUser
import com.example.core.domain.entity.SearchUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserList(query: String): Flow<Result<List<SearchUser>>>
    fun getUserDetail(username: String): Flow<Result<DetailUser>>
}
