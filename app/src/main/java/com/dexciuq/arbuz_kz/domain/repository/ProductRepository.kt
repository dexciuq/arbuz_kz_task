package com.dexciuq.arbuz_kz.domain.repository

import com.dexciuq.arbuz_kz.domain.model.Product
import com.dexciuq.arbuz_kz.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getAllProducts(): Flow<Resource<List<Product>>>
    suspend fun getProductById(id: Long): Flow<Resource<Product>>
    fun getAllProductsFromCart(): Flow<Resource<List<Product>>>
    suspend fun addProductToCart(product: Product)
    suspend fun removeProductFromCart(product: Product)
    suspend fun removeAllProductFromCart()
}