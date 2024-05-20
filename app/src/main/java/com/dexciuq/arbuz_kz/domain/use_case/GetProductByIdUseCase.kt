package com.dexciuq.arbuz_kz.domain.use_case

import com.dexciuq.arbuz_kz.domain.model.Product
import com.dexciuq.arbuz_kz.domain.repository.ProductRepository
import com.dexciuq.arbuz_kz.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: Long): Flow<Resource<Product>> =
        withContext(Dispatchers.IO) {
            repository.getProductById(id)
        }
}