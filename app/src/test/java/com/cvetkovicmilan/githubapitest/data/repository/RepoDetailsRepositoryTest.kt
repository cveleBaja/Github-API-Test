package com.cvetkovicmilan.githubapitest.data.repository

import com.cvetkovicmilan.githubapitest.data.remote.network.ApiService
import com.cvetkovicmilan.githubapitest.data.remote.network.ResponseWrapper
import com.cvetkovicmilan.githubapitest.domain.repository.RepoDetailsRepository
import com.cvetkovicmilan.githubapitest.util.Faker
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class RepoDetailsRepositoryTest {

    @Mock
    private lateinit var mockApiService: ApiService
    private lateinit var repoDetailsRepository: RepoDetailsRepository

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repoDetailsRepository = RepoDetailsRepositoryImpl(mockApiService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get repo details returns failure`() = runTest {
        // Arrange
        val user = "Unkown user"
        val repoName = "repo name"
        Mockito.`when`(mockApiService.fetchRepoDetails(user, repoName)).thenAnswer {  throw Exception("Failure") }

        // Act
        val result = repoDetailsRepository.getRepoDetails(user, repoName)

        // Assert
        Mockito.verify(mockApiService, Mockito.times(1))
            .fetchRepoDetails(user, repoName)
        Truth.assertThat(result).isInstanceOf(ResponseWrapper.Failure::class.java)
    }

    @Test
    fun `get repo details returns success`() = runTest {
        // Arrange
        val user = "Octocat"
        val repoName = "repoName"
        Mockito.`when`(mockApiService.fetchRepoDetails(user, repoName)).thenReturn(Faker.getRepoDetailsDto())

        // Act
        val result = repoDetailsRepository.getRepoDetails(user, repoName)

        // Assert
        Mockito.verify(mockApiService, Mockito.times(1))
            .fetchRepoDetails(user, repoName)
        Truth.assertThat(result).isInstanceOf(ResponseWrapper.Success::class.java)
    }

    @Test
    fun `get repo tags returns failure`() = runTest {
        // Arrange
        val user = "Unkown user"
        val repoName = "repo name"
        Mockito.`when`(mockApiService.fetchTagsForRepo(user, repoName)).thenAnswer {  throw Exception("Failure") }

        // Act
        val result = repoDetailsRepository.getRepoTags(user, repoName)

        // Assert
        Mockito.verify(mockApiService, Mockito.times(1))
            .fetchTagsForRepo(user, repoName)
        Truth.assertThat(result).isInstanceOf(ResponseWrapper.Failure::class.java)
    }

    @Test
    fun `get repo tags returns success`() = runTest {
        // Arrange
        val user = "Octocat"
        val repoName = "repoName"
        Mockito.`when`(mockApiService.fetchTagsForRepo(user, repoName)).thenReturn(Faker.getRepoTagDtos())

        // Act
        val result = repoDetailsRepository.getRepoTags(user, repoName)

        // Assert
        Mockito.verify(mockApiService, Mockito.times(1))
            .fetchTagsForRepo(user, repoName)
        Truth.assertThat(result).isInstanceOf(ResponseWrapper.Success::class.java)
    }
}