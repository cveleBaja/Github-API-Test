package com.cvetkovicmilan.githubapitest.presentation.screen.repo_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cvetkovicmilan.githubapitest.data.remote.network.ResponseWrapper
import com.cvetkovicmilan.githubapitest.domain.repository.RepoDetailsRepository
import com.cvetkovicmilan.githubapitest.util.Constants
import com.cvetkovicmilan.githubapitest.util.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val repoDetailsRepository: RepoDetailsRepository,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private var _uiState = MutableStateFlow<RepoDetailsScreenState>(RepoDetailsScreenState.Loading)
    val uiState get() = _uiState.asStateFlow()

    init {
        savedStateHandle.get<String>(Constants.REPO_NAME)?.let {
            getRepoDetails(it)
        }
    }

    fun getRepoDetails(repoName: String) {
        _uiState.value = RepoDetailsScreenState.Loading

        viewModelScope.launch {
            val repoDetailsDeferred = async {
                repoDetailsRepository.getRepoDetails(
                    Constants.DEFAULT_USER_NAME,
                    repoName
                )
            }
            val repoTagsDeferred =
                async { repoDetailsRepository.getRepoTags(Constants.DEFAULT_USER_NAME, repoName) }

            val repoDetailsResult = repoDetailsDeferred.await()
            val repoTagsResult = repoTagsDeferred.await()

            if (repoDetailsResult is ResponseWrapper.Failure) {
                _uiState.value = RepoDetailsScreenState.Error(repoDetailsResult.message)
                return@launch
            }

            if (repoTagsResult is ResponseWrapper.Failure) {
                _uiState.value = RepoDetailsScreenState.Error(repoTagsResult.message)
                return@launch
            }

            val repoDetails = repoDetailsResult as ResponseWrapper.Success
            val repoTags = repoTagsResult as ResponseWrapper.Success

            _uiState.value = RepoDetailsScreenState.Success(repoDetails.data, repoTags.data)
        }
    }
}