package com.dexciuq.arbuz_kz.data.repository

import com.dexciuq.arbuz_kz.data.data_source.DataSource
import com.dexciuq.arbuz_kz.domain.model.Product
import com.dexciuq.arbuz_kz.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : ProductRepository {
    override fun getAllProducts(): Flow<List<Product>> {
        return dataSource.getAllProducts()
    }

    override fun getAllProductsFromCart(): Flow<List<Product>> {
        return dataSource.getAllProductsFromCart()
    }

    override suspend fun insertProductToCart(product: Product) {
        dataSource.insertProductToCart(product)
    }

    override suspend fun deleteProductFromCart(product: Product) {
        dataSource.deleteProductFromCart(product)
    }

    override suspend fun deleteAllProductFromCart() {
        dataSource.deleteAllProductFromCart()
    }
}