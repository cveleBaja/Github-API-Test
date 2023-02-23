package com.cvetkovicmilan.githubapitest.presentation.screen.repo_details

import com.cvetkovicmilan.githubapitest.domain.model.RepoTag
import com.cvetkovicmilan.githubapitest.domain.model.UserRepo

sealed class RepoDetailsScreenState {
    object Loading: RepoDetailsScreenState()
    data class Error(val error: String) : RepoDetailsScreenState()
    data class Success(val data: UserRepo, val tags: List<RepoTag>) : RepoDetailsScreenState()
}