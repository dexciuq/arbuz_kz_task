package com.dexciuq.arbuz_kz.domain.use_case

import com.dexciuq.arbuz_kz.domain.model.Product
import com.dexciuq.arbuz_kz.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddProductToCartUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: Product) =
        withContext(Dispatchers.IO) {
            repository.addProductToCart(product)
        }
}