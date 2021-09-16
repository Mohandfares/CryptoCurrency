package com.dz.cryptocurrency.presentation.peopleprofile

import com.dz.cryptocurrency.domain.model.PeopleProfile

data class PeopleProfileState(
    val isLoading: Boolean = false,
    val peopleProfile: PeopleProfile? = null,
    val error: String = ""
)
