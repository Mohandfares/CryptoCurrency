package com.dz.cryptocurrency.domain.usecase.gettag

import com.dz.cryptocurrency.common.Resource
import com.dz.cryptocurrency.data.remote.dto.toTagDetail
import com.dz.cryptocurrency.domain.model.TagDetail
import com.dz.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTagUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(tagId: String): Flow<Resource<TagDetail>> = flow {
        try {
            emit(Resource.Loading<TagDetail>())
            val tagDetail = repository.getTagById(tagId).toTagDetail()
            emit(Resource.Success<TagDetail>(tagDetail))
        } catch (e: HttpException) {
            emit(Resource.Error<TagDetail>(e.localizedMessage ?: "An unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error<TagDetail>("Make sure you are connected to the internet and try again"))
        }
    }
}