package com.cvetkovicmilan.githubapitest.di

import com.cvetkovicmilan.githubapitest.data.repository.RepoDetailsRepositoryImpl
import com.cvetkovicmilan.githubapitest.data.repository.UserReposRepositoryImpl
import com.cvetkovicmilan.githubapitest.domain.repository.RepoDetailsRepository
import com.cvetkovicmilan.githubapitest.domain.repository.UserReposRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserReposRepository(
        authRepositoryImpl: UserReposRepositoryImpl
    ): UserReposRepository

    @Binds
    abstract fun bindRepoDetailsRepository(
        repoDetailsRepositoryImpl: RepoDetailsRepositoryImpl
    ): RepoDetailsRepository
}