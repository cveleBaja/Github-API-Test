package com.cvetkovicmilan.githubapitest.data.mapper

import com.cvetkovicmilan.githubapitest.data.remote.model.RepoTagDto
import com.cvetkovicmilan.githubapitest.domain.model.RepoTag

object RepoTagMapper: DomainMapper<RepoTagDto, RepoTag> {

    override fun mapToDomainModel(model: RepoTagDto): RepoTag {
        return RepoTag(
            name = model.name,
            sha = model.commit.sha,
            url = model.commit.url
        )
    }

    override fun mapFromDomainModel(domainModel: RepoTag): RepoTagDto {
        TODO("Not yet implemented")
    }
}