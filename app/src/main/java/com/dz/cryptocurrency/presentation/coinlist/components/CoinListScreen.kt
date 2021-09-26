package com.dz.cryptocurrency.presentation.coinlist.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dz.cryptocurrency.presentation.coinlist.CoinListViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.dz.cryptocurrency.R
import com.dz.cryptocurrency.presentation.Screen
import com.dz.cryptocurrency.presentation.common.EmptyStateUI
import com.dz.cryptocurrency.ui.theme.Teal200


@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(
                        color = Teal200,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(10.dp)
            ) {
                Text(
                    text = stringResource(R.string.cryptocurrencylist),
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.Black
                )
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.coins) { coin ->
                    CoinListItem(
                        coin = coin,
                        onClickItem = { navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}") }
                    )
                }
            }
        }

        if (state.error.isNotBlank()) {
            EmptyStateUI(
                error = state.error,
                modifier = Modifier.align(Alignment.Center),
                onClick = { viewModel.getCoins() }
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}