package com.dz.cryptocurrency.data.remote.dto

data class LinksProfile(
    val additional: List<Additional>,
    val github: List<Github>,
    val linkedin: List<Linkedin>,
    val medium: List<Medium>,
    val twitter: List<Twitter>
)