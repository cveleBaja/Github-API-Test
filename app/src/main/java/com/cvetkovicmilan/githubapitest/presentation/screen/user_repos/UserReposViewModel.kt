package com.cvetkovicmilan.githubapitest.presentation.screen.user_repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cvetkovicmilan.githubapitest.data.remote.network.ResponseWrapper
import com.cvetkovicmilan.githubapitest.domain.repository.UserReposRepository
import com.cvetkovicmilan.githubapitest.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserReposViewModel @Inject constructor(private val userReposRepository: UserReposRepository) :
    ViewModel() {

    private var _uiState = MutableStateFlow<UserReposScreenState>(UserReposScreenState.Loading)
    val uiState get() = _uiState.asStateFlow()

    init {
        getUserRepos(Constants.DEFAULT_USER_NAME)
    }

    fun getUserRepos(user: String) {
        _uiState.value = UserReposScreenState.Loading

        viewModelScope.launch {
            when (val result = userReposRepository.getReposForUser(user)) {
                is ResponseWrapper.Failure -> {
                    _uiState.value = UserReposScreenState.Error(result.message)
                }
                is ResponseWrapper.Success -> {
                    _uiState.value = UserReposScreenState.Success(result.data)
                }
            }
        }
    }
}