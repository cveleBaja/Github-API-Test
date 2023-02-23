package com.cvetkovicmilan.githubapitest.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cvetkovicmilan.githubapitest.presentation.theme.GithubAPITestTheme
import com.cvetkovicmilan.githubapitest.presentation.theme.White

@Composable
fun CustomProgressBar() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
fun CustomProgressBarPreview() {
    GithubAPITestTheme {
        CustomProgressBar()
    }
}