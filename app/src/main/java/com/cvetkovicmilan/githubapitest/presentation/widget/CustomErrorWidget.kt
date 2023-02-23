package com.cvetkovicmilan.githubapitest.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cvetkovicmilan.githubapitest.presentation.theme.GithubAPITestTheme
import com.cvetkovicmilan.githubapitest.presentation.theme.White

@Composable
fun CustomErrorWidget(message: String, onButtonPressed: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            onClick = onButtonPressed,
        ) {
            Text("Try Again")
        }
    }
}

@Preview
@Composable
fun CustomErrorWidgetPreview() {
    GithubAPITestTheme {
        CustomErrorWidget(
            message = "Something went wrong",
            onButtonPressed = {}
        )
    }
}