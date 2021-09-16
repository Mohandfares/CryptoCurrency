package com.dz.cryptocurrency.presentation.twitterlist.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.dz.cryptocurrency.domain.model.Twitter
import com.dz.cryptocurrency.domain.model.formatDate
import com.dz.cryptocurrency.ui.theme.CryptoCurrencyTheme
import com.dz.cryptocurrency.ui.theme.MediumGray
import com.dz.cryptocurrency.ui.theme.TwitterColor

@Composable
fun TwitterListItem(
    twitter: Twitter
) {
    Row(
        modifier = Modifier
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        RoundImage(
            url = twitter.userImageLink,
            modifier = Modifier
                .size(56.dp)
                .weight(3f)
        )
        Spacer(modifier = Modifier.width(30.dp))
        StatusDetail(twitter = twitter)
    }
}

@Composable
fun RoundImage(
    url: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = rememberImagePainter(url),
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
fun StatusDetail(
    twitter: Twitter,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                val boldStyle = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                val mediumGrayStyle = SpanStyle(
                    fontWeight = FontWeight.Medium,
                    color = MediumGray
                )
                pushStyle(boldStyle)
                append(twitter.username)
                pop()
                pushStyle(mediumGrayStyle)
                append(" @${twitter.username.lowercase()}")
                pop()
            }
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = twitter.formatDate(),
            color = TwitterColor,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = buildAnnotatedString {
            val hashTagStyle = SpanStyle(
                color = TwitterColor,
                fontWeight = FontWeight.Bold
            )
            val descriptionArray = twitter.status.split(" ")
            descriptionArray.forEach { s ->
                when {
                    s.contains("@") -> {
                        append(s.substringBefore("@"))
                        pushStyle(hashTagStyle)
                        append("@")
                        append(s.substringAfter("@"))
                        append(" ")
                        pop()
                    }
                    s.contains("#") -> {
                        append(s.substringBefore("#"))
                        pushStyle(hashTagStyle)
                        append("#")
                        append(s.substringAfter("#"))
                        append(" ")
                        pop()
                    }
                    s.contains("https://") -> {
                        pushStyle(hashTagStyle)
                        append(s)
                        append(" ")
                        pop()
                    }
                    else -> {
                        append("$s ")
                    }
                }
            }
        })
        Spacer(modifier = Modifier.height(5.dp))
        Divider()
    }
}

@Preview(name = "Light Mode", showSystemUi = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun DefaultPreview() {
    CryptoCurrencyTheme {
        TwitterListItem(
            twitter = Twitter(
                "2021-07-20T21:51:31Z",
                "PSA: @BitcoinCoreOrg (#Bitcoin Core) is NOT soliciting or performing closed testing of any beta or other software. If you get an email inviting you, be aware it is spam and their testing version is almost certainly malware. Note: the From on emails is NOT secure and faked.",
                "",
                0,
                "http://pbs.twimg.com/profile_images/690997344960167940/WpD5FDxr_normal.png",
                "bitcoincoreorg"
            )
        )
    }
}