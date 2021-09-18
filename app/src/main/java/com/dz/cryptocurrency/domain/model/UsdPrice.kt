package com.dz.cryptocurrency.domain.model

import java.text.DecimalFormat

data class UsdPrice(
    val price: Double
)

fun UsdPrice.toPriceFormat(): String {
    val format = when {
        price >= 10000 -> DecimalFormat("#0,000.00")
        price >= 100 -> DecimalFormat("#000.00")
        price >= 10 -> DecimalFormat("#00.00")
        else -> DecimalFormat("0.00")
    }
    return format.format(price).replace(",", ".")
}
