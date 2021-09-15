package com.dz.cryptocurrency.data.remote.dto

import com.dz.cryptocurrency.domain.model.Twitter
import com.google.gson.annotations.SerializedName

data class TwitterDto(
    val date: String,
    @SerializedName("is_retweet")
    val isRetweet: Boolean,
    @SerializedName("like_count")
    val likeCount: Int,
    @SerializedName("retweet_count")
    val retweetCount: Int,
    val status: String,
    @SerializedName("status_id")
    val statusId: String,
    @SerializedName("status_link")
    val statusLink: String,
    @SerializedName("user_image_link")
    val userImageLink: String,
    @SerializedName("user_name")
    val username: String
)

fun TwitterDto.toTwitter(): Twitter =
    Twitter(
        date = date,
        status = status,
        statusLink = statusLink,
        likeCount = likeCount,
        userImageLink = userImageLink,
        username = username
    )