package com.dz.cryptocurrency.presentation.twitterlist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dz.cryptocurrency.R
import com.dz.cryptocurrency.presentation.emptystateui.EmptyStateUI
import com.dz.cryptocurrency.presentation.twitterlist.TwitterListViewModel

@Composable
fun TwitterListScreen(
    viewModel: TwitterListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Image(
                        painter = painterResource(id = R.drawable.twitter),
                        modifier = Modifier
                            .size(36.dp)
                            .align(Alignment.CenterHorizontally),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Divider()
                }
            }
            items(state.twitters) { twitter ->
                TwitterListItem(twitter = twitter)
            }
        }

        if (state.twitters.isEmpty()) {
            EmptyStateUI(
                error = "No Twitter status !",
                modifier = Modifier.align(Alignment.Center),
                tryAgain = false
            )
        }

        if (state.error.isNotBlank()) {
            EmptyStateUI(
                error = state.error,
                modifier = Modifier.align(Alignment.Center),
                onClick = { viewModel.tryAgain() }
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}