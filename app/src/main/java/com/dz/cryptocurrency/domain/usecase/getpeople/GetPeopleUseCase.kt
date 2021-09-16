package com.dz.cryptocurrency.domain.usecase.getpeople

import com.dz.cryptocurrency.common.Resource
import com.dz.cryptocurrency.data.remote.dto.toPeopleProfile
import com.dz.cryptocurrency.domain.model.PeopleProfile
import com.dz.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPeopleUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(peopleId: String): Flow<Resource<PeopleProfile>> = flow {
        try {
            emit(Resource.Loading<PeopleProfile>())
            val people = repository.getPeople(peopleId).toPeopleProfile()
            emit(Resource.Success<PeopleProfile>(people))
        } catch (e: HttpException) {
            emit(Resource.Error<PeopleProfile>(e.localizedMessage ?: "An unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error<PeopleProfile>("Make sure you are connected to the internet and try again"))
        }
    }
}