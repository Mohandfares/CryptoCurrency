package com.dz.cryptocurrency.data.remote.dto

import com.dz.cryptocurrency.domain.model.TagDetail
import com.google.gson.annotations.SerializedName

data class TagDetailDto(
    @SerializedName("coin_counter")
    val coinCounter: Int,
    val description: String,
    @SerializedName("ico_counter")
    val icoCounter: Int,
    val id: String,
    val name: String,
    val type: String
)

fun TagDetailDto.toTagDetail(): TagDetail =
    TagDetail(
        id = id,
        name = name,
        type = type,
        description = description,
        coinCounter = coinCounter
    )