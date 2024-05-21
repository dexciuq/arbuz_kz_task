package com.dexciuq.arbuz_kz.presentation.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dexciuq.arbuz_kz.domain.model.Product
import com.dexciuq.arbuz_kz.domain.use_case.DeleteAllProductFromCartUseCase
import com.dexciuq.arbuz_kz.domain.use_case.DeleteProductFromCartUseCase
import com.dexciuq.arbuz_kz.domain.use_case.GetAllProductsFromCartUseCase
import com.dexciuq.arbuz_kz.domain.use_case.InsertProductToCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getAllProductsFromCartUseCase: GetAllProductsFromCartUseCase,
    private val insertProductToCartUseCase: InsertProductToCartUseCase,
    private val deleteProductFromCartUseCase: DeleteProductFromCartUseCase,
    private val deleteAllProductFromCartUseCase: DeleteAllProductFromCartUseCase,
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products get() = _products.asStateFlow()

    init {
        getAllProductsFromCart()
    }

    private fun getAllProductsFromCart() = viewModelScope.launch {
        getAllProductsFromCartUseCase().collectLatest {
            _products.emit(it)
        }
    }

    fun clearAll() = viewModelScope.launch {
        deleteAllProductFromCartUseCase()
    }

    fun updateProduct(product: Product) = viewModelScope.launch {
        if (product.quantity == 0) {
            deleteProductFromCartUseCase(product)
        } else {
            insertProductToCartUseCase(product)
        }
    }
}