package com.dz.cryptocurrency.presentation.coindetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dz.cryptocurrency.common.Constants.PARAM_COIN_ID
import com.dz.cryptocurrency.common.Resource
import com.dz.cryptocurrency.domain.usecase.getcoin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val coinUseCase: GetCoinUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<CoinDetailState>(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let {
            getCoin(it)
        }
    }

    private fun getCoin(coinId: String) {
        coinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Error -> _state.value = CoinDetailState(error = result.message ?: "An unexpected error")
                is Resource.Loading -> _state.value = CoinDetailState(isLoading = true)
                is Resource.Success -> _state.value = CoinDetailState(coin = result.data)
            }
        }.launchIn(viewModelScope)
    }

    fun tryAgain() {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let {
            getCoin(it)
        }
    }
}