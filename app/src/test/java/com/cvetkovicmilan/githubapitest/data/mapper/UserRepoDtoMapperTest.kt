package com.cvetkovicmilan.githubapitest.data.mapper

import com.cvetkovicmilan.githubapitest.data.mapper.UserRepoDtoMapper.mapToDomainModel
import com.cvetkovicmilan.githubapitest.domain.model.UserRepo
import com.cvetkovicmilan.githubapitest.util.Faker
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UserRepoDtoMapperTest {

    @Test
    fun `convert dto to domain model success`() {
        val userRepoDtos = listOf(Faker.getRepoDetailsDto())

        val userRepo = userRepoDtos.map { mapToDomainModel(it) }.first()

        assertThat(userRepo).isInstanceOf(UserRepo::class.java)
        assertThat(userRepo.id).isInstanceOf(String::class.java)
        assertThat(userRepo.name).isInstanceOf(String::class.java)
        assertThat(userRepo.openIssuesCount).isInstanceOf(Integer::class.java)
        assertThat(userRepo.htmlUrl).isInstanceOf(String::class.java)
        assertThat(userRepo.forksCount).isInstanceOf(Integer::class.java)
        assertThat(userRepo.watchersCount).isInstanceOf(Integer::class.java)
        assertThat(userRepo.ownerName).isInstanceOf(String::class.java)
        assertThat(userRepo.ownerAvatarUrl).isInstanceOf(String::class.java)
    }
}