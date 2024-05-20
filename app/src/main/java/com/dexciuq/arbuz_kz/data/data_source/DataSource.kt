package com.dexciuq.arbuz_kz.data.data_source

import com.dexciuq.arbuz_kz.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun getAllProducts(): Flow<List<Product>>
    fun getAllProductsFromCart(): Flow<List<Product>>
    suspend fun insertProductToCart(product: Product)
    suspend fun deleteProductFromCart(product: Product)
    suspend fun deleteAllProductFromCart()
}