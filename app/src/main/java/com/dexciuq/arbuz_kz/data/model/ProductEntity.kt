package com.dexciuq.arbuz_kz.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class ProductEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val description: String,
    val image: Int,
    val price: Long,
    val productUnit: String,
    val quantity: Int,
)