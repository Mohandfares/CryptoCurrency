package com.dz.cryptocurrency.presentation.tagdetail

import com.dz.cryptocurrency.domain.model.TagDetail

data class TagDetailState(
    val isLoading: Boolean = false,
    val tagDetail: TagDetail? = null,
    val error: String = ""
)
