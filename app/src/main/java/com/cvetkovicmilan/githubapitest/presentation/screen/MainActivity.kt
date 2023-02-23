package com.cvetkovicmilan.githubapitest.presentation.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cvetkovicmilan.githubapitest.presentation.screen.repo_details.RepoDetailsScreen
import com.cvetkovicmilan.githubapitest.presentation.screen.user_repos.UserReposScreen
import com.cvetkovicmilan.githubapitest.presentation.theme.GithubAPITestTheme
import com.cvetkovicmilan.githubapitest.util.Constants
import com.cvetkovicmilan.githubapitest.util.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubAPITestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    Scaffold {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.UserRepos.route,
                            modifier = Modifier.padding(it)
                        ) {
                            composable(route = Screen.UserRepos.route) {
                                UserReposScreen(navController)
                            }
                            composable(route = Screen.RepoDetails.route) { backStackEntry ->
                                backStackEntry.arguments?.getString(Constants.REPO_NAME)?.let { repoName ->
                                    RepoDetailsScreen(navController, repoName)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}