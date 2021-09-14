package com.dz.cryptocurrency.presentation.coinlist.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dz.cryptocurrency.presentation.coinlist.CoinListViewModel
import androidx.compose.ui.Modifier
import com.dz.cryptocurrency.R
import com.dz.cryptocurrency.presentation.Screen
import com.dz.cryptocurrency.presentation.emptystateui.EmptyStateUI
import com.dz.cryptocurrency.ui.theme.DarkGray


@Composable
fun CoinListScreen(
    navController: NavController,
    context: Context,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text(text = context.getString(R.string.cryptocurrencylist)) },
                backgroundColor = DarkGray
            )
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.coins) { coin ->
                    CoinListItem(
                        coin = coin,
                        onClickItem = { navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")}
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