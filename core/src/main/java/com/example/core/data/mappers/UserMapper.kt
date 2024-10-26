package com.example.core.data.mappers

import com.example.core.data.models.DetailUserModel
import com.example.core.data.models.SearchUserModel
import com.example.core.domain.entity.DetailUser
import com.example.core.domain.entity.SearchUser

object UserMapper {

    // Mapping dari DetailUserModel ke DetailUser (domain entity)
    fun fromDetailUserModel(detailUserModel: DetailUserModel): DetailUser {
        return DetailUser(
            login = detailUserModel.login,
            id = detailUserModel.id,
            nodeId = detailUserModel.nodeId,
            avatarUrl = detailUserModel.avatarUrl,
            gravatarId = detailUserModel.gravatarId,
            url = detailUserModel.url,
            htmlUrl = detailUserModel.htmlUrl,
            followersUrl = detailUserModel.followersUrl,
            followingUrl = detailUserModel.followingUrl,
            gistsUrl = detailUserModel.gistsUrl,
            starredUrl = detailUserModel.starredUrl,
            subscriptionsUrl = detailUserModel.subscriptionsUrl,
            organizationsUrl = detailUserModel.organizationsUrl,
            reposUrl = detailUserModel.reposUrl,
            eventsUrl = detailUserModel.eventsUrl,
            receivedEventsUrl = detailUserModel.receivedEventsUrl,
            type = detailUserModel.type,
            siteAdmin = detailUserModel.siteAdmin,
            name = detailUserModel.name,
            company = detailUserModel.company,
            blog = detailUserModel.blog,
            location = detailUserModel.location,
            email = detailUserModel.email,
            hireable = detailUserModel.hireable,
            bio = detailUserModel.bio,
            twitterUsername = detailUserModel.twitterUsername,
            publicRepos = detailUserModel.publicRepos,
            publicGists = detailUserModel.publicGists,
            followers = detailUserModel.followers,
            following = detailUserModel.following,
            createdAt = detailUserModel.createdAt,
            updatedAt = detailUserModel.updatedAt
        )
    }

    // Mapping dari SearchUserModel ke SearchUser (domain entity)
    fun fromSearchUserModel(searchUserModel: SearchUserModel): SearchUser {
        return SearchUser(
            login = searchUserModel.login,
            id = searchUserModel.id,
            nodeId = searchUserModel.nodeId,
            avatarUrl = searchUserModel.avatarUrl,
            gravatarId = searchUserModel.gravatarId,
            url = searchUserModel.url,
            htmlUrl = searchUserModel.htmlUrl,
            followersUrl = searchUserModel.followersUrl,
            followingUrl = searchUserModel.followingUrl,
            gistsUrl = searchUserModel.gistsUrl,
            starredUrl = searchUserModel.starredUrl,
            subscriptionsUrl = searchUserModel.subscriptionsUrl,
            organizationsUrl = searchUserModel.organizationsUrl,
            reposUrl = searchUserModel.reposUrl,
            eventsUrl = searchUserModel.eventsUrl,
            receivedEventsUrl = searchUserModel.receivedEventsUrl,
            type = searchUserModel.type,
            siteAdmin = searchUserModel.siteAdmin,
            score = searchUserModel.score
        )
    }
}
