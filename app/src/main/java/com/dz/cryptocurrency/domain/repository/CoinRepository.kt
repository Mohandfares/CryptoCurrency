package com.dz.cryptocurrency.domain.repository

import com.dz.cryptocurrency.data.remote.dto.CoinDetailDto
import com.dz.cryptocurrency.data.remote.dto.CoinDto
import com.dz.cryptocurrency.data.remote.dto.TagDetailDto
import com.dz.cryptocurrency.data.remote.dto.TwitterDto


interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto

    suspend fun getTagById(tagId: String): TagDetailDto

    suspend fun getTwitter(coinId: String): List<TwitterDto>
}