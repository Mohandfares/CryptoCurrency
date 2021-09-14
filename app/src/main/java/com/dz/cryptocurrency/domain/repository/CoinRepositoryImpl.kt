package com.dz.cryptocurrency.domain.repository

import com.dz.cryptocurrency.data.remote.CoinPaprikaApi
import com.dz.cryptocurrency.data.remote.dto.CoinDetailDto
import com.dz.cryptocurrency.data.remote.dto.CoinDto
import com.dz.cryptocurrency.data.remote.dto.TagDetailDto
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> = api.getCoins()
    override suspend fun getCoinById(coinId: String): CoinDetailDto = api.getCoin(coinId)
    override suspend fun getTagById(tagId: String): TagDetailDto = api.getTag(tagId)
}