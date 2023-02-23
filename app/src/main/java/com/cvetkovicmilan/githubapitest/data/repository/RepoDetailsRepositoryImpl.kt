package com.cvetkovicmilan.githubapitest.data.repository

import com.cvetkovicmilan.githubapitest.data.mapper.UserRepoDtoMapper.mapToDomainModel
import com.cvetkovicmilan.githubapitest.data.mapper.RepoTagMapper.mapToDomainModel as repoMapper
import com.cvetkovicmilan.githubapitest.data.remote.network.ApiService
import com.cvetkovicmilan.githubapitest.data.remote.network.ResponseWrapper
import com.cvetkovicmilan.githubapitest.data.remote.network.safeApiCall
import com.cvetkovicmilan.githubapitest.domain.model.RepoTag
import com.cvetkovicmilan.githubapitest.domain.model.UserRepo
import com.cvetkovicmilan.githubapitest.domain.repository.RepoDetailsRepository
import javax.inject.Inject

class RepoDetailsRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    RepoDetailsRepository {

    override suspend fun getRepoDetails(user: String, repoName: String): ResponseWrapper<UserRepo> {
        return safeApiCall {
            mapToDomainModel(apiService.fetchRepoDetails(user, repoName))
        }
    }

    override suspend fun getRepoTags(
        user: String,
        repoName: String
    ): ResponseWrapper<List<RepoTag>> {
        return safeApiCall {
            val dtos = apiService.fetchTagsForRepo(user, repoName)
            dtos.map { repoMapper(it) }
        }
    }
}