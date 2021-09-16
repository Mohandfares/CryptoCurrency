package com.dz.cryptocurrency.presentation.peopleprofile.components

import com.dz.cryptocurrency.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dz.cryptocurrency.data.remote.dto.Position
import com.dz.cryptocurrency.domain.model.PeopleProfile
import com.dz.cryptocurrency.presentation.emptystateui.EmptyStateUI
import com.dz.cryptocurrency.presentation.peopleprofile.PeopleProfileViewModel
import com.dz.cryptocurrency.ui.theme.ColorPrimary
import com.dz.cryptocurrency.ui.theme.Teal200


@Composable
fun PeopleProfileScreen(
    viewModel: PeopleProfileViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.peopleProfile?.let { peopleProfile ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item { 
                    HeaderProfile(
                        peopleProfile = peopleProfile,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Center)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Positions",
                        style = MaterialTheme.typography.h2
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }

                items(peopleProfile.positions) { position ->
                    PositionItem(
                        position = position,
                        modifier = Modifier.fillMaxWidth().padding(5.dp)
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

@Composable
fun HeaderProfile(
    peopleProfile: PeopleProfile,
    modifier: Modifier = Modifier)
{
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoundImageProfile(
            painter = painterResource(id = R.drawable.profile),
            modifier = modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = peopleProfile.name,
            style = MaterialTheme.typography.h2,
            color = Teal200,
        )
    }
}

@Composable
fun RoundImageProfile(
    painter: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun PositionItem(
    position: Position,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${position.coinName} (${position.coinSymbol})",
            style = MaterialTheme.typography.h4,
            color = ColorPrimary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = position.position,
            style = MaterialTheme.typography.body2,
            fontStyle = FontStyle.Italic
        )
    }
}