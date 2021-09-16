package com.dz.cryptocurrency.data.remote.dto

import com.dz.cryptocurrency.domain.model.PeopleProfile
import com.google.gson.annotations.SerializedName

data class PeopleProfileDto(
    val description: String?,
    val id: String,
    val links: LinksProfile,
    val name: String,
    val positions: List<Position>,
    @SerializedName("teams_count")
    val teamsCount: Int
)

fun PeopleProfileDto.toPeopleProfile(): PeopleProfile =
    PeopleProfile(
        name = name,
        description = description ?: "",
        positions = positions,
        links = links
    )