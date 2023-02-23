package com.cvetkovicmilan.githubapitest.domain.repository

import com.cvetkovicmilan.githubapitest.data.remote.network.ResponseWrapper
import com.cvetkovicmilan.githubapitest.domain.model.UserRepo

interface UserReposRepository {

    suspend fun getReposForUser(user: String): ResponseWrapper<List<UserRepo>>
}