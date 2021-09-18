package com.dz.cryptocurrency.data.remote.dto

data class LinksProfile(
    val additional: List<Additional>?,
    val github: List<Github>?,
    val linkedin: List<Linkedin>?,
    val medium: List<Medium>?,
    val twitter: List<Twitter>?
)

fun LinksProfile.linksSize(): Int {
    var size = 0
    if (!additional.isNullOrEmpty()) {
        size += additional.size
    }
    if (!github.isNullOrEmpty()) {
        size += github.size
    }
    if (!linkedin.isNullOrEmpty()) {
        size += linkedin.size
    }
    if (!medium.isNullOrEmpty()) {
        size += medium.size
    }
    if (!twitter.isNullOrEmpty()) {
        size += twitter.size
    }
    return size
}