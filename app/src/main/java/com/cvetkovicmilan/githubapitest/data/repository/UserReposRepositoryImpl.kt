package com.cvetkovicmilan.githubapitest.data.repository

import com.cvetkovicmilan.githubapitest.data.mapper.UserRepoDtoMapper.mapToDomainModel
import com.cvetkovicmilan.githubapitest.data.remote.network.ApiService
import com.cvetkovicmilan.githubapitest.data.remote.network.ResponseWrapper
import com.cvetkovicmilan.githubapitest.data.remote.network.safeApiCall
import com.cvetkovicmilan.githubapitest.domain.model.UserRepo
import com.cvetkovicmilan.githubapitest.domain.repository.UserReposRepository
import javax.inject.Inject

class UserReposRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    UserReposRepository {

    override suspend fun getReposForUser(user: String): ResponseWrapper<List<UserRepo>> {
        return safeApiCall {
            val dtos = apiService.fetchReposForUser(user)
            dtos.map { mapToDomainModel(it) }
        }
    }
}