package com.dz.cryptocurrency.domain.model

import java.text.SimpleDateFormat
import java.util.*

data class Twitter(
    val date: String,
    val status: String,
    val statusLink: String,
    val likeCount: Int,
    val userImageLink: String,
    val username: String
)

fun Twitter.formatDate(): String {
    val originFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val format = SimpleDateFormat("EEE dd MMM yyyy HH:mm")
    val oldDate: Date = originFormat.parse(date.replace("z","").replace("T"," "))
    return format.format(oldDate)
}
