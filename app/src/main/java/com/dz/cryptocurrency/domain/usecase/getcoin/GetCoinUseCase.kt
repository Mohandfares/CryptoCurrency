package com.dz.cryptocurrency.domain.usecase.getcoin

import com.dz.cryptocurrency.common.Resource
import com.dz.cryptocurrency.data.remote.dto.toCoinDetail
import com.dz.cryptocurrency.data.remote.dto.toUSD
import com.dz.cryptocurrency.domain.model.CoinDetail
import com.dz.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val price = repository.getTickers(coinId).toUSD()
            val coin = repository.getCoinById(coinId).toCoinDetail().copy(usdPrice = price)
            emit(Resource.Success<CoinDetail>(coin))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetail>("Make sure you are connected to the internet and try again"))
        }
    }
}