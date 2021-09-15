package com.dz.cryptocurrency.presentation

sealed class Screen(val route: String) {
    object CoinListScreen : Screen("coin_list_screen")
    object CoinDetailScreen : Screen("coin_detail_screen")
    object TagDetailScreen : Screen("tag_detail_screen")
    object TwitterListScreen : Screen("twitter_list_screen")
}