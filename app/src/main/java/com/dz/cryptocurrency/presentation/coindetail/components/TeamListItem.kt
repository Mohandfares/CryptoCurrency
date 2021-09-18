package com.dz.cryptocurrency.presentation.coindetail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dz.cryptocurrency.R
import com.dz.cryptocurrency.data.remote.dto.TeamMember
import com.dz.cryptocurrency.presentation.common.RoundImageProfile
import com.dz.cryptocurrency.ui.theme.CryptoCurrencyTheme


@Composable
fun TeamListItem(
    teamMember: TeamMember,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RoundImageProfile(
            painter = painterResource(id = R.drawable.profile),
            modifier = modifier.size(40.dp)
        )
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = teamMember.name,
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = teamMember.position,
                style = MaterialTheme.typography.body2,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    CryptoCurrencyTheme {
        TeamListItem(
            modifier = Modifier
                .padding(10.dp),
            teamMember = TeamMember("","Mohaned fares","CEO $ Fandateur")
        )
    }
}