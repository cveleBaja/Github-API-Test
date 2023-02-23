package com.cvetkovicmilan.githubapitest.data.remote.model

data class RepoTagDto(
    val name: String,
    val commit: Commit
) {
    data class Commit(
        val sha: String,
        val url: String
    )
}
