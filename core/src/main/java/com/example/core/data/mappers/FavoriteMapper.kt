package com.example.core.data.mappers

import com.example.core.data.local.entities.FavoriteEntity
import com.example.core.domain.entity.Favorite

fun FavoriteEntity.toDomainModel(): Favorite {
    return Favorite(
        username = this.username,
        avatarUrl = this.avatarUrl
    )
}

fun Favorite.toEntity(): FavoriteEntity {
    return FavoriteEntity(
        username = this.username,
        avatarUrl = this.avatarUrl
    )
}