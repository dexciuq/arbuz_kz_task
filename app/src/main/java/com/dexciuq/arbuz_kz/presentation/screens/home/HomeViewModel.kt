package com.dexciuq.arbuz_kz.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dexciuq.arbuz_kz.domain.model.Product
import com.dexciuq.arbuz_kz.domain.use_case.DeleteProductFromCartUseCase
import com.dexciuq.arbuz_kz.domain.use_case.GetAllProductsUseCase
import com.dexciuq.arbuz_kz.domain.use_case.InsertProductToCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val insertProductToCartUseCase: InsertProductToCartUseCase,
    private val deleteProductFromCartUseCase: DeleteProductFromCartUseCase,
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products get() = _products.asStateFlow()

    init {
        getAllProducts()
    }

    private fun getAllProducts() = viewModelScope.launch {
        getAllProductsUseCase().collectLatest {
            _products.emit(it)
        }
    }

    fun updateProduct(product: Product) = viewModelScope.launch {
        if (product.quantity == 0) {
            deleteProductFromCartUseCase(product)
        } else {
            insertProductToCartUseCase(product)
        }
    }
}