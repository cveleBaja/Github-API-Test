package com.cvetkovicmilan.githubapitest.presentation.screen.user_repos

import com.cvetkovicmilan.githubapitest.domain.model.UserRepo

sealed class UserReposScreenState {
    object Loading : UserReposScreenState()
    data class Error(val error: String) : UserReposScreenState()
    data class Success(val data: List<UserRepo>) : UserReposScreenState()
}