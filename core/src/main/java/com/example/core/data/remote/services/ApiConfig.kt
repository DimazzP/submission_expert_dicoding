package com.example.core.data.remote.services

import com.example.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    // Fungsi untuk menyediakan instance Retrofit dan ApiService
    fun getApiService(): ApiService {
        val apiKey = BuildConfig.API_KEY
        val baseUrl = BuildConfig.BASE_URL

        // Interceptor untuk Authorization
        val authInterceptor = Interceptor { chain ->
            val req = chain.request()
            val requestHeaders = req.newBuilder()
                .addHeader("Authorization", apiKey)
                .build()
            chain.proceed(requestHeaders)
        }

        // Interceptor untuk logging
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Log level BODY untuk detail lengkap
        }

        // OkHttpClient dengan authInterceptor dan loggingInterceptor
        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor) // Tambahkan logging interceptor di sini
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }

//    companion object {
//        fun getApiService(): ApiService {
//            val apiKey = BuildConfig.API_KEY
//            val baseUrl = BuildConfig.BASE_URL
//            val authInterceptor = Interceptor { chain ->
//                val req = chain.request()
//                val requestHeaders = req.newBuilder()
//                    .addHeader("Authorization", apiKey)
//                    .build()
//                chain.proceed(requestHeaders)
//            }
//            val client = OkHttpClient.Builder()
//                .addInterceptor(authInterceptor)
//                .build()
//            val retrofit = Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//            return retrofit.create(ApiService::class.java)
//        }
//    }
}