package com.cvetkovicmilan.githubapitest.data.remote.model

data class UserRepoDto(
    val id: String,
    val name: String,
    val open_issues_count: Int,
    val html_url: String,
    val forks_count: Int,
    val watchers_count: Int,
    val owner: Owner
) {
    data class Owner(
        val login: String,
        val avatar_url: String
    )
}
