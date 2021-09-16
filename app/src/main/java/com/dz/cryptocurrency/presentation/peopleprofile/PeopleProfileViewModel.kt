package com.dz.cryptocurrency.presentation.peopleprofile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dz.cryptocurrency.common.Constants.PARAM_PEOPLE_ID
import com.dz.cryptocurrency.common.Resource
import com.dz.cryptocurrency.domain.usecase.getpeople.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PeopleProfileViewModel @Inject constructor(
    private val peopleUseCase: GetPeopleUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PeopleProfileState())
    val state: State<PeopleProfileState> = _state

    init {
        savedStateHandle.get<String?>(PARAM_PEOPLE_ID)?.let { peopleId ->
            getPeople(peopleId)
        }
    }

    private fun getPeople(peopleId: String) {
        peopleUseCase(peopleId).onEach { result ->
            when (result) {
                is Resource.Error -> _state.value = PeopleProfileState(error = result.message ?: "An unexpected error")
                is Resource.Loading -> _state.value = PeopleProfileState(isLoading = true)
                is Resource.Success -> _state.value = PeopleProfileState(peopleProfile = result.data)
            }
        }.launchIn(viewModelScope)
    }

    fun tryAgain() {
        savedStateHandle.get<String?>(PARAM_PEOPLE_ID)?.let { peopleId ->
            getPeople(peopleId)
        }
    }
}