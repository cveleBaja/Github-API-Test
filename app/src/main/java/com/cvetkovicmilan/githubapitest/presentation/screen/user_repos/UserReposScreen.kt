package com.cvetkovicmilan.githubapitest.presentation.screen.user_repos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cvetkovicmilan.githubapitest.domain.model.UserRepo
import com.cvetkovicmilan.githubapitest.presentation.theme.GithubAPITestTheme
import com.cvetkovicmilan.githubapitest.presentation.theme.White
import com.cvetkovicmilan.githubapitest.presentation.widget.CustomErrorWidget
import com.cvetkovicmilan.githubapitest.presentation.widget.CustomProgressBar
import com.cvetkovicmilan.githubapitest.util.Constants
import com.cvetkovicmilan.githubapitest.util.Faker
import com.cvetkovicmilan.githubapitest.util.navigation.Screen

@Composable
fun UserReposScreen(navController: NavController) {

    val viewModel: UserReposViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        is UserReposScreenState.Error -> {
            CustomErrorWidget(
                message = uiState.error,
                onButtonPressed = {
                    viewModel.getUserRepos(Constants.DEFAULT_USER_NAME)
                }
            )
        }
        UserReposScreenState.Loading -> {
            CustomProgressBar()
        }
        is UserReposScreenState.Success -> {
            UserReposScreenContent(
                data = uiState.data,
                onRepoPressed = { repoName ->
                    navController.navigate(
                        Screen.RepoDetails.createRoute(
                            repoName
                        )
                    )
                }
            )
        }
    }
}

@Composable
private fun UserReposScreenContent(data: List<UserRepo>, onRepoPressed: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .background(White)
    ) {
        itemsIndexed(data) { index, item ->
            UserRepoCell(index, item) { repoName ->
                onRepoPressed(repoName)
            }
        }
    }
}

@Composable
private fun UserRepoCell(index: Int, data: UserRepo, onRepoPressed: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable { onRepoPressed(data.name) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$index. ",
            fontSize = 12.sp
        )
        Text(
            text = data.name,
            fontSize = 18.sp,
        )
        Text(
            text = data.openIssuesCount.toString(),
            color = data.openIssuesCountColor,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun UserRepoCellPreview() {
    GithubAPITestTheme {
        UserRepoCell(
            index = 1,
            data = Faker.getUserRepo(),
            onRepoPressed = {}
        )
    }
}