package com.dz.cryptocurrency.presentation.twitterlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dz.cryptocurrency.common.Constants.PARAM_COIN_ID
import com.dz.cryptocurrency.common.Resource
import com.dz.cryptocurrency.domain.usecase.gettwitter.GetTwitterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TwitterListViewModel @Inject constructor(
    private val twitterUseCase: GetTwitterUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(TwitterListState())
    val state: State<TwitterListState> = _state

    init {
        savedStateHandle.get<String?>(PARAM_COIN_ID)?.let { coinId ->
            getTwitters(coinId)
        }
    }

    private fun getTwitters(coinId: String) {
        twitterUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Error -> TwitterListState(error = result.message ?: "An unexpected error")
                is Resource.Loading -> TwitterListState(isLoading = true)
                is Resource.Success -> _state.value = TwitterListState(twitters = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }

    fun tryAgain() {
        savedStateHandle.get<String?>(PARAM_COIN_ID)?.let { coinId ->
            getTwitters(coinId)
        }
    }
}