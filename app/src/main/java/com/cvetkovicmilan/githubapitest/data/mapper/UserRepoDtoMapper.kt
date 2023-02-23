package com.cvetkovicmilan.githubapitest.data.mapper

import com.cvetkovicmilan.githubapitest.data.remote.model.UserRepoDto
import com.cvetkovicmilan.githubapitest.domain.model.UserRepo
import com.cvetkovicmilan.githubapitest.presentation.theme.Green
import com.cvetkovicmilan.githubapitest.presentation.theme.Red

object UserRepoDtoMapper: DomainMapper<UserRepoDto, UserRepo> {

    override fun mapToDomainModel(model: UserRepoDto): UserRepo {
        return UserRepo(
            id = model.id,
            name = model.name,
            openIssuesCount = model.open_issues_count,
            openIssuesCountColor = if (model.open_issues_count == 0) Green else Red,
            htmlUrl = model.html_url,
            forksCount = model.forks_count,
            forksCountColor = if (model.forks_count == 0) Green else Red,
            watchersCount = model.watchers_count,
            watchersCountColor = if (model.watchers_count == 0) Green else Red,
            ownerName = model.owner.login,
            ownerAvatarUrl = model.owner.avatar_url
        )
    }

    override fun mapFromDomainModel(domainModel: UserRepo): UserRepoDto {
        TODO("Not yet implemented")
    }
}