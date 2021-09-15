package com.dz.cryptocurrency.domain.usecase.gettwitter

import com.dz.cryptocurrency.common.Resource
import com.dz.cryptocurrency.data.remote.dto.toTwitter
import com.dz.cryptocurrency.domain.model.Twitter
import com.dz.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTwitterUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<List<Twitter>>> = flow {
        try {
            emit(Resource.Loading<List<Twitter>>())
            val twitters = repository.getTwitter(coinId).map { it.toTwitter() }
            emit(Resource.Success<List<Twitter>>(twitters))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Twitter>>(e.localizedMessage ?: "An unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Twitter>>("Make sure you are connected to the internet and try again"))
        }
    }
}