package com.dz.cryptocurrency.data.remote.dto

import com.dz.cryptocurrency.domain.model.Coin
import com.dz.cryptocurrency.domain.model.UsdPrice
import com.google.gson.annotations.SerializedName

data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoin(): Coin =
    Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol,
        usdPrice = UsdPrice(0.0)
    )