package com.cvetkovicmilan.githubapitest.data.remote.network

import com.cvetkovicmilan.githubapitest.data.remote.model.RepoTagDto
import com.cvetkovicmilan.githubapitest.data.remote.model.UserRepoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{user}/repos")
    suspend fun fetchReposForUser(@Path("user") user: String): List<UserRepoDto>

    @GET("repos/{user}/{repoName}")
    suspend fun fetchRepoDetails(
        @Path("user") user: String,
        @Path("repoName") repoName: String
    ): UserRepoDto

    @GET("repos/{user}/{repoName}/tags")
    suspend fun fetchTagsForRepo(
        @Path("user") user: String,
        @Path("repoName") repoName: String
    ): List<RepoTagDto>
}