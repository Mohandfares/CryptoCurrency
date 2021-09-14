package com.dz.cryptocurrency.presentation.tagdetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dz.cryptocurrency.common.Constants.PARAM_TAG_ID
import com.dz.cryptocurrency.common.Resource
import com.dz.cryptocurrency.domain.usecase.gettag.GetTagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TagDetailViewModel @Inject constructor(
    private val tagUseCase: GetTagUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(TagDetailState())
    val state: State<TagDetailState> = _state

    init {
        savedStateHandle.get<String>(PARAM_TAG_ID)?.let { tagId ->
            getTag(tagId)
        }
    }

    private fun getTag(tagId: String) {
        tagUseCase(tagId).onEach { result ->
            when (result) {
                is Resource.Error -> _state.value = TagDetailState(error = result.message ?: "An unexpected error")
                is Resource.Loading -> _state.value = TagDetailState(isLoading = true)
                is Resource.Success -> _state.value = TagDetailState(tagDetail = result.data)
            }
        }.launchIn(viewModelScope)
    }

    fun tryAgain() {
        savedStateHandle.get<String>(PARAM_TAG_ID)?.let { tagId ->
            getTag(tagId)
        }
    }
}