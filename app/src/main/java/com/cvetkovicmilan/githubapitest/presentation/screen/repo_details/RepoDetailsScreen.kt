package com.cvetkovicmilan.githubapitest.presentation.screen.repo_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.cvetkovicmilan.githubapitest.domain.model.RepoTag
import com.cvetkovicmilan.githubapitest.domain.model.UserRepo
import com.cvetkovicmilan.githubapitest.presentation.theme.GithubAPITestTheme
import com.cvetkovicmilan.githubapitest.presentation.theme.White
import com.cvetkovicmilan.githubapitest.presentation.widget.CustomErrorWidget
import com.cvetkovicmilan.githubapitest.presentation.widget.CustomProgressBar
import com.cvetkovicmilan.githubapitest.util.Faker

@Composable
fun RepoDetailsScreen(navController: NavController, repoName: String) {

    val viewModel: RepoDetailsViewModel = hiltViewModel()

    when (val uiState = viewModel.uiState.collectAsState().value) {
        is RepoDetailsScreenState.Error -> {
            CustomErrorWidget(
                message = uiState.error,
                onButtonPressed = { viewModel.getRepoDetails(repoName) }
            )
        }
        RepoDetailsScreenState.Loading -> {
            CustomProgressBar()
        }
        is RepoDetailsScreenState.Success -> {
            RepoDetailsScreenContent(uiState.data, uiState.tags)
        }
    }
}

@Composable
private fun RepoDetailsScreenContent(data: UserRepo, tags: List<RepoTag>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .background(White)
            .verticalScroll(rememberScrollState())
    ) {
        HeaderContent(data)
        Text(
            text = "Tags:",
            modifier = Modifier.padding(vertical = 20.dp)
        )
        if (tags.isEmpty()) {
            Text(
                text = "No tags for this repository"
            )
            return@Column
        }
        tags.forEach {
            RepoTagCell(it)
        }
    }
}

@Composable
fun HeaderContent(data: UserRepo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(data.ownerAvatarUrl),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
        ) {
            Text(
                text = data.ownerName,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )
            CountCell(
                text = "Forks count: ",
                count = data.forksCount.toString(),
                countColor = data.forksCountColor
            )
            CountCell(
                text = "Watchers count: ",
                count = data.watchersCount.toString(),
                countColor = data.watchersCountColor
            )
        }
    }
}

@Composable
private fun RepoTagCell(tag: RepoTag) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        Text(
            text = "Version:",
            fontSize = 12.sp
        )
        Text(
            text = tag.name,
            fontSize = 13.sp
        )
        Text(
            text = "Sha:",
            fontSize = 12.sp,
            modifier = Modifier
                .padding(top = 5.dp)
        )
        Text(
            text = tag.sha,
            fontSize = 13.sp
        )
    }
}

@Composable
private fun CountCell(text: String, count: String, countColor: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            fontSize = 12.sp
        )
        Text(
            text = count,
            color = countColor,
            fontSize = 13.sp
        )
    }
}


@Preview
@Composable
fun HeaderContentPreview() {
    GithubAPITestTheme {
        RepoDetailsScreenContent(
            data = Faker.getUserRepo(),
            tags = Faker.getTags()
        )
    }
}
