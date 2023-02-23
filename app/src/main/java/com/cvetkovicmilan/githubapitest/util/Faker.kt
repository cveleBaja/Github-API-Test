package com.cvetkovicmilan.githubapitest.util

import com.cvetkovicmilan.githubapitest.data.remote.model.RepoTagDto
import com.cvetkovicmilan.githubapitest.data.remote.model.UserRepoDto
import com.cvetkovicmilan.githubapitest.domain.model.RepoTag
import com.cvetkovicmilan.githubapitest.domain.model.UserRepo
import com.cvetkovicmilan.githubapitest.presentation.theme.Green
import com.cvetkovicmilan.githubapitest.presentation.theme.Red

object Faker {

    fun getUserRepo() = UserRepo(
        id = "1",
        name = "git-consortium",
        openIssuesCount = 12,
        openIssuesCountColor = Red,
        htmlUrl = "https://github.com/octocat/git-consortium",
        forksCount = 1,
        forksCountColor = Red,
        watchersCount = 0,
        watchersCountColor = Green,
        ownerName = "Octocat",
        ownerAvatarUrl = "https://avatars.githubusercontent.com/u/583231?v=4"
    )

    fun getTags() = mutableListOf(
        RepoTag(
            name = "v1.0.0",
            sha = "sha123",
            url = "url"
        ),
        RepoTag(
            name = "v1.0.1",
            sha = "sha123",
            url = "url"
        ),
        RepoTag(
            name = "v1.0.2",
            sha = "sha123",
            url = "url"
        ),
        RepoTag(
            name = "v1.0.3",
            sha = "sha123",
            url = "url"
        ),
        RepoTag(
            name = "v1.0.4",
            sha = "sha123",
            url = "url"
        ),
        RepoTag(
            name = "v1.0.5",
            sha = "sha123",
            url = "url"
        ),
        RepoTag(
            name = "v1.0.6",
            sha = "sha123",
            url = "url"
        ),
        RepoTag(
            name = "v1.0.7",
            sha = "sha123",
            url = "url"
        ),
        RepoTag(
            name = "v1.0.8",
            sha = "sha123",
            url = "url"
        ),
        RepoTag(
            name = "v1.0.9",
            sha = "sha123",
            url = "url"
        ),
        RepoTag(
            name = "v1.0.10",
            sha = "sha123",
            url = "url"
        ),
        RepoTag(
            name = "v1.0.11",
            sha = "sha123",
            url = "url"
        ),
        RepoTag(
            name = "v1.0.12",
            sha = "sha123",
            url = "url"
        )
    )

    fun getRepoDetailsDto() = UserRepoDto(
        id = "1",
        name = "git-consortium",
        open_issues_count = 12,
        html_url = "https://github.com/octocat/git-consortium",
        forks_count = 1,
        watchers_count = 0,
        owner = UserRepoDto.Owner(
            login = "Octocat",
            avatar_url = "https://avatars.githubusercontent.com/u/583231?v=4"
        ),
    )

    fun getRepoTagDtos() = listOf(
        RepoTagDto(
            name = "v1.0.0",
            commit = RepoTagDto.Commit(
                sha = "sha123",
                url = "url"
            )
        )
    )
}