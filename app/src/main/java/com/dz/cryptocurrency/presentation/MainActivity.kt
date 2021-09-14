package com.dz.cryptocurrency.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dz.cryptocurrency.common.Constants.PARAM_COIN_ID
import com.dz.cryptocurrency.common.Constants.PARAM_TAG_ID
import com.dz.cryptocurrency.presentation.coindetail.components.CoinDetailScreen
import com.dz.cryptocurrency.presentation.coinlist.components.CoinListScreen
import com.dz.cryptocurrency.presentation.tagdetail.components.TagDetailScreen
import com.dz.cryptocurrency.ui.theme.CryptoCurrencyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCurrencyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ) {
                        composable(
                            route = Screen.CoinListScreen.route
                        ) {
                            CoinListScreen(
                                context = this@MainActivity,
                                navController = navController
                            )
                        }

                        composable(
                            route = Screen.CoinDetailScreen.route + "/{${PARAM_COIN_ID}}"
                        ) {
                            CoinDetailScreen(navController = navController)
                        }

                        composable(
                            route = Screen.TagDetailScreen.route + "/{${PARAM_TAG_ID}}"
                        ) {
                            TagDetailScreen()
                        }
                    }
                }
            }
        }
    }


}