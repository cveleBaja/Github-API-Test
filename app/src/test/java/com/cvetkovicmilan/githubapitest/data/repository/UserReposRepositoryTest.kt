package com.cvetkovicmilan.githubapitest.data.repository

import com.cvetkovicmilan.githubapitest.data.remote.network.ApiService
import com.cvetkovicmilan.githubapitest.data.remote.network.ResponseWrapper
import com.cvetkovicmilan.githubapitest.domain.repository.UserReposRepository
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
class UserReposRepositoryTest {

    @Mock
    private lateinit var mockApiService: ApiService
    private lateinit var userReposRepository: UserReposRepository

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        userReposRepository = UserReposRepositoryImpl(mockApiService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get user repos returns failure`() = runTest {
        // Arrange
        val user = "Unkown user"
        Mockito.`when`(mockApiService.fetchReposForUser(user)).thenAnswer {  throw Exception("Failure") }

        // Act
        val result = userReposRepository.getReposForUser(user)

        // Assert
        Mockito.verify(mockApiService, Mockito.times(1))
            .fetchReposForUser(user)
        Truth.assertThat(result).isInstanceOf(ResponseWrapper.Failure::class.java)
    }

    @Test
    fun `get user repos returns success`() = runTest {
        // Arrange
        val user = "Octocat"
        Mockito.`when`(mockApiService.fetchReposForUser(user)).thenReturn(listOf())

        // Act
        val result = userReposRepository.getReposForUser(user)

        // Assert
        Mockito.verify(mockApiService, Mockito.times(1))
            .fetchReposForUser(user)
        Truth.assertThat(result).isInstanceOf(ResponseWrapper.Success::class.java)
    }
}