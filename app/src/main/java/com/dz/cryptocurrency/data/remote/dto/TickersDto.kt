package com.dz.cryptocurrency.data.remote.dto

import com.dz.cryptocurrency.domain.model.Coin
import com.dz.cryptocurrency.domain.model.UsdPrice
import com.google.gson.annotations.SerializedName

data class TickersDto(
    @SerializedName("beta_value")
    val betaValue: Double,
    @SerializedName("circulating_supply")
    val circulatingSupply: Double,
    @SerializedName("first_data_at")
    val firstDataAt: String,
    val id: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("max_supply")
    val maxSupply: Double,
    val name: String,
    val quotes: Quotes?,
    val rank: Int,
    val symbol: String,
    @SerializedName("total_supply")
    val totalSupply: Double
)

fun TickersDto.toUSD(): UsdPrice =
    UsdPrice(quotes?.USD?.price ?: 0.0)

fun TickersDto.toCoin(): Coin =
        Coin(
            id = id,
            name = name,
            rank = rank,
            symbol = symbol,
            usdPrice = UsdPrice(quotes?.USD?.price ?: 0.0),
            isActive = true
        )