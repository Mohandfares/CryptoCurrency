package com.dz.cryptocurrency.domain.repository

import com.dz.cryptocurrency.data.remote.dto.CoinDetailDto
import com.dz.cryptocurrency.data.remote.dto.CoinDto


interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}