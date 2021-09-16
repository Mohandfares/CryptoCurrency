package com.dz.cryptocurrency.domain.model

import com.dz.cryptocurrency.data.remote.dto.LinksProfile
import com.dz.cryptocurrency.data.remote.dto.Position

data class PeopleProfile(
    val description: String,
    val links: LinksProfile,
    val name: String,
    val positions: List<Position>
)
