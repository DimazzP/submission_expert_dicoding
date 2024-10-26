package com.example.core.data.remote.services

import com.example.core.data.models.DetailUserModel
import com.example.core.data.models.SearchUserModel
import com.example.core.data.remote.responses.ResponseUser
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun getListUsers(@Query("q") query: String): Response<ResponseUser>

    // Mendapatkan detail user berdasarkan username
    @GET("users/{username}")
    suspend fun getDetailUser(@Path("username") username: String): Response<DetailUserModel>

    //    @GET("search/users")
//    fun getListUsers(@Query("q") page: String): Call<ResponseUser>
//
//    @GET("users/{username}")
//    fun getDetailUser(@Path("username") username: String): Call<DetailUserModel>

//    @GET("users/{username}/followers")
//    fun getUserFollowers(@Path("username") username: String): Call<List<SearchUserModel>>

//    @GET("users/{username}/following")
//    fun getUserFollowing(@Path("username") username: String): Call<List<SearchUserModel>>

    // Mendapatkan daftar user berdasarkan query
}