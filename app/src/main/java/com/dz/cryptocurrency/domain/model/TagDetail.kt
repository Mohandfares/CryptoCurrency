package com.dz.cryptocurrency.domain.model

import com.dz.cryptocurrency.data.remote.dto.Tag

data class TagDetail(
    val id: String,
    val name: String,
    val type: String,
    val coinCounter: Int,
    val description: String
)

fun TagDetail.getTag(): Tag =
    Tag(
        id = id,
        name = name,
        coinCounter = coinCounter,
        icoCounter = 0
    )
