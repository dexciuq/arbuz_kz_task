package com.dexciuq.arbuz_kz.domain.use_case

import com.dexciuq.arbuz_kz.utils.Resource
import com.dexciuq.arbuz_kz.domain.model.Product
import com.dexciuq.arbuz_kz.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsFromCartUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<Product>>> =
        repository.getAllProductsFromCart()
}