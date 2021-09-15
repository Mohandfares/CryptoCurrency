package com.dz.cryptocurrency.presentation.emptystateui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dz.cryptocurrency.R

@Composable
fun EmptyStateUI(error: String,modifier: Modifier,onClick: () -> Unit = {}, tryAgain: Boolean = true) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.emptystate),
            modifier = Modifier.size(250.dp).align(Alignment.CenterHorizontally),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(15.dp))
        if (tryAgain) {
            Button(
                onClick = { onClick() },
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Text(text = "Try again")
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
        Text(
            text = error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
}