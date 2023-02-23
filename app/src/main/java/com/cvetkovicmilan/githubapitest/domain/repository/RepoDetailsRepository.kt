package com.cvetkovicmilan.githubapitest.domain.repository

import com.cvetkovicmilan.githubapitest.data.remote.network.ResponseWrapper
import com.cvetkovicmilan.githubapitest.domain.model.RepoTag
import com.cvetkovicmilan.githubapitest.domain.model.UserRepo

interface RepoDetailsRepository {

    suspend fun getRepoDetails(user: String, repoName: String): ResponseWrapper<UserRepo>

    suspend fun getRepoTags(user: String, repoName: String): ResponseWrapper<List<RepoTag>>
}