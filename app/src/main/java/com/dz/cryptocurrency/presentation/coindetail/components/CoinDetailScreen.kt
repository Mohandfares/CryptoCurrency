package com.dz.cryptocurrency.presentation.coindetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dz.cryptocurrency.R
import com.dz.cryptocurrency.data.remote.dto.TeamMember
import com.dz.cryptocurrency.presentation.Screen
import com.dz.cryptocurrency.presentation.coindetail.CoinDetailViewModel
import com.dz.cryptocurrency.presentation.emptystateui.EmptyStateUI
import com.dz.cryptocurrency.ui.theme.ColorPrimary
import com.dz.cryptocurrency.ui.theme.Teal200
import com.dz.cryptocurrency.ui.theme.TwitterColor
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoinDetailScreen(
    navController: NavController,
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.coin?.let { coinDetail ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {

                item {
                    Row(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = TwitterColor,
                                shape = RoundedCornerShape(100.dp)
                            )
                            .clickable {
                                navController.navigate(Screen.TwitterListScreen.route + "/${coinDetail.coinId}")
                            }
                            .align(Alignment.Center)
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.twitter),
                            modifier = Modifier.size(24.dp),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "${coinDetail.name} twitter",
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.Medium,
                            color = TwitterColor,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${coinDetail.rank}. ${coinDetail.name} (${coinDetail.symbol})",
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier.weight(8f)
                        )
                        Text(
                            text = if (coinDetail.isActive) "active" else "inactive",
                            color = if (coinDetail.isActive) Teal200 else ColorPrimary,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(2f)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = coinDetail.description,
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    if (coinDetail.tags.isNotEmpty()) {
                        Text(
                            text = "Tags",
                            style = MaterialTheme.typography.h3
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        FlowRow(
                            mainAxisSpacing = 10.dp,
                            crossAxisSpacing = 10.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            coinDetail.tags.forEach { tag ->
                                CoinTag(
                                    tag = tag,
                                    onClickItem = {
                                        navController.navigate(Screen.TagDetailScreen.route + "/${tag.id}")
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                    }

                    if (coinDetail.teamMember.isNotEmpty()) {
                        Text(
                            text = "Team members",
                            style = MaterialTheme.typography.h3
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
                items(coinDetail.teamMember) { teamMember ->
                    TeamListItem(
                        teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable { navController.navigate(Screen.PeopleProfileScreen.route + "/${teamMember.id}") }
                    )
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