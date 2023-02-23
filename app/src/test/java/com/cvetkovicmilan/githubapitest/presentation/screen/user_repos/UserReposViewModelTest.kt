package com.cvetkovicmilan.githubapitest.presentation.screen.user_repos

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
class UserReposViewModelTest {

    @Mock
    private lateinit var mockUserReposRepository: UserReposRepository
    private lateinit var userReposViewModel: UserReposViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        userReposViewModel = UserReposViewModel(mockUserReposRepository)
    }

    @Test
    fun `get repo details sets ui state to error`(): Unit = runTest {
        // Arrange
        val user = "Unkown user"
        Mockito.`when`(mockUserReposRepository.getReposForUser(user))
            .thenReturn(ResponseWrapper.Failure("error message"))

        // Act
        userReposViewModel.getUserRepos(user)

        // Assert
        Mockito.verify(mockUserReposRepository, Mockito.times(1))
            .getReposForUser(user)
        Truth.assertThat(userReposViewModel.uiState.value)
            .isInstanceOf(UserReposScreenState.Error::class.java)
    }

    @Test
    fun `get repo details sets ui state to success`(): Unit = runTest {
        // Arrange
        val user = "Octocat"
        Mockito.`when`(mockUserReposRepository.getReposForUser(user))
            .thenReturn(ResponseWrapper.Success(listOf()))

        // Act
        userReposViewModel.getUserRepos(user)

        // Assert
        Mockito.verify(mockUserReposRepository, Mockito.times(1))
            .getReposForUser(user)
        Truth.assertThat(userReposViewModel.uiState.value)
            .isInstanceOf(UserReposScreenState.Success::class.java)
    }
}