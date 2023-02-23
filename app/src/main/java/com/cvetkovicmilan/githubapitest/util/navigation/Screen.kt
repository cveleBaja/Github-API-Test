package com.cvetkovicmilan.githubapitest.util.navigation

import com.cvetkovicmilan.githubapitest.util.Constants

sealed class Screen(val route: String) {
    object UserRepos: Screen("userRepos")
    object RepoDetails: Screen("repoDetails/{${Constants.REPO_NAME}}") {
        fun createRoute(repoName: String) = "repoDetails/$repoName"
    }
}