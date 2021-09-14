package com.dz.cryptocurrency.data.remote

import com.dz.cryptocurrency.data.remote.dto.CoinDetailDto
import com.dz.cryptocurrency.data.remote.dto.CoinDto
import com.dz.cryptocurrency.data.remote.dto.TagDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoin(@Path("coinId") coinId: String): CoinDetailDto

    @GET("/v1/tags/{tagId}")
    suspend fun getTag(@Path("tagId") tagId: String): TagDetailDto
}