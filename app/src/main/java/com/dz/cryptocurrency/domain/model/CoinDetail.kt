package com.dz.cryptocurrency.domain.model

import com.dz.cryptocurrency.data.remote.dto.Tag
import com.dz.cryptocurrency.data.remote.dto.TeamMember

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<Tag>,
    val teamMember: List<TeamMember>,
    val usdPrice: UsdPrice
)
