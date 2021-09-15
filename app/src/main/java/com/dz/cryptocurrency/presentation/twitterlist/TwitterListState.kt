package com.dz.cryptocurrency.presentation.twitterlist

import com.dz.cryptocurrency.domain.model.Twitter

data class TwitterListState(
    val isLoading: Boolean = false,
    val twitters: List<Twitter> = emptyList(),
    val error: String = ""
)
