package com.example.core.data.models

sealed class ResultUser<out R> private constructor() {
    data class Success<out T>(val data: T) : ResultUser<T>()
    data class Error(val error: String) : ResultUser<Nothing>()
    object Loading : ResultUser<Nothing>()
}