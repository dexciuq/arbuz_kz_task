package com.dexciuq.arbuz_kz.domain.model

import androidx.annotation.DrawableRes

data class Product(
    val id: Long,
    val name: String,
    val description: String,
    @DrawableRes
    val image: Int,
    val price: Long,
    val productUnit: ProductUnit,
    val quantity: Int = 0,
) {
    fun getPrice(): String = "${this.price / 100} ₸ / ${this.productUnit.name.lowercase()}"
}
