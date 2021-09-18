package com.dz.cryptocurrency.domain.usecase.getcoins

import com.dz.cryptocurrency.common.Resource
import com.dz.cryptocurrency.data.remote.dto.toCoin
import com.dz.cryptocurrency.domain.model.Coin
import com.dz.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            // val coins = repository.getCoins().map { it.toCoin() } old method ...
            val coins = repository.getTickers().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("Make sure you are connected to the internet and try again"))
        }
    }
}