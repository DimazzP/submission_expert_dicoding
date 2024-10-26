package com.example.core.data.remote.responses

import com.example.core.data.models.SearchUserModel
import com.google.gson.annotations.SerializedName

data class ResponseUser(
    @field:SerializedName("total_count")
    val totalCount: Int? = null,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,

    @field:SerializedName("items")
    val items: List<SearchUserModel>? = null
)