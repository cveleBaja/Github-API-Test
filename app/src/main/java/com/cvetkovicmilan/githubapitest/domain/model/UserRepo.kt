package com.cvetkovicmilan.githubapitest.domain.model

import androidx.compose.ui.graphics.Color

data class UserRepo(
    val id: String,
    val name: String,
    val openIssuesCount: Int,
    val openIssuesCountColor: Color,
    val htmlUrl: String,
    val forksCount: Int,
    val forksCountColor: Color,
    val watchersCount: Int,
    val watchersCountColor: Color,
    val ownerName: String,
    val ownerAvatarUrl: String
)
