package com.dz.cryptocurrency.presentation.tagdetail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dz.cryptocurrency.domain.model.getTag
import com.dz.cryptocurrency.presentation.coindetail.components.CoinTag
import com.dz.cryptocurrency.presentation.common.EmptyStateUI
import com.dz.cryptocurrency.presentation.tagdetail.TagDetailViewModel
import com.dz.cryptocurrency.ui.theme.Teal200


@Composable
fun TagDetailScreen(
    viewModel: TagDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.tagDetail?.let { tagDetail ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Text(
                        text = "Tag detail",
                        style = MaterialTheme.typography.h2
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    CoinTag(tag = tagDetail.getTag())
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = tagDetail.description,
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "The type is ${tagDetail.type}",
                        style = MaterialTheme.typography.h3,
                        color = Teal200
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Coin Counter = ${tagDetail.coinCounter}",
                        style = MaterialTheme.typography.h3
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Divider()
                }
            }
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