package com.dexciuq.arbuz_kz.domain.use_case

import com.dexciuq.arbuz_kz.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteAllProductFromCartUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke() =
        withContext(Dispatchers.IO) {
            repository.deleteAllProductFromCart()
        }
}