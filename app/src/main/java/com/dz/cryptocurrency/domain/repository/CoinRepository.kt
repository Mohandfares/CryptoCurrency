package com.dz.cryptocurrency.domain.repository

import com.dz.cryptocurrency.data.remote.dto.*

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto

    suspend fun getTagById(tagId: String): TagDetailDto

    suspend fun getTwitter(coinId: String): List<TwitterDto>

    suspend fun getPeople(peopleId: String): PeopleProfileDto

    suspend fun getTickers(): List<TickersDto>

    suspend fun getTickers(coinId: String): TickersDto
}