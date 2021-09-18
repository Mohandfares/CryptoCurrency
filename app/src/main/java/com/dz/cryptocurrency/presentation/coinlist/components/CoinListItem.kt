package com.dz.cryptocurrency.presentation.coinlist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dz.cryptocurrency.domain.model.Coin
import com.dz.cryptocurrency.domain.model.toPriceFormat
import com.dz.cryptocurrency.ui.theme.ColorPrimary
import com.dz.cryptocurrency.ui.theme.Teal200

@Composable
fun CoinListItem(
    coin: Coin,
    onClickItem: (Coin) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickItem(coin) }
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "${coin.name} (${coin.symbol})",
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Ranking N° ${coin.rank}",
                style = MaterialTheme.typography.body1,
                color = ColorPrimary
            )
        }

        Text(
            text = "${coin.usdPrice.toPriceFormat()} USD",
            color = Teal200,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.align(CenterVertically)
        )
    }
}