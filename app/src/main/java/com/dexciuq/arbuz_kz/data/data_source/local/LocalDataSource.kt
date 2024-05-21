package com.dexciuq.arbuz_kz.data.data_source.local

import com.dexciuq.arbuz_kz.R
import com.dexciuq.arbuz_kz.data.data_source.DataSource
import com.dexciuq.arbuz_kz.data.data_source.local.db.ProductDao
import com.dexciuq.arbuz_kz.data.mapper.toDomain
import com.dexciuq.arbuz_kz.data.mapper.toProductEntity
import com.dexciuq.arbuz_kz.domain.model.Product
import com.dexciuq.arbuz_kz.domain.model.ProductUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val productDao: ProductDao,
) : DataSource {

    private val productList: List<Product> = getProductList()

    override fun getAllProducts(): Flow<List<Product>> = flow {
        productDao.getAll().collect { cardProducts ->
            val updatedProductList = productList.map { product ->
                val matchingCardProduct = cardProducts.find { it.id == product.id }
                if (matchingCardProduct != null) {
                    product.quantity = matchingCardProduct.quantity
                } else {
                    product.quantity = 0
                }
                product
            }
            emit(updatedProductList)
        }
    }

    override fun getAllProductsFromCart(): Flow<List<Product>> = flow {
        productDao.getAll().collect {
            emit(it.toDomain())
        }
    }

    override suspend fun insertProductToCart(product: Product) {
        productDao.insert(product.toProductEntity())
    }

    override suspend fun deleteProductFromCart(product: Product) {
        productDao.delete(product.toProductEntity())
    }

    override suspend fun deleteAllProductFromCart() {
        productDao.deleteAll()
    }

    private fun getProductList(): List<Product> = listOf(
        Product(
            id = 1,
            name = "Apple",
            description = "Fresh red apples",
            image = R.drawable.ic_apple,
            price = 55000,
            productUnit = ProductUnit.KG
        ),
        Product(
            id = 2,
            name = "Banana",
            description = "Ripe yellow bananas",
            image = R.drawable.ic_banana,
            price = 90000,
            productUnit = ProductUnit.KG
        ),
        Product(
            id = 3,
            name = "Carrot",
            description = "Organic carrots",
            image = R.drawable.ic_carrot,
            price = 25000,
            productUnit = ProductUnit.KG
        ),
        Product(
            id = 4,
            name = "Milk",
            description = "Fresh cow milk",
            image = R.drawable.ic_milk,
            price = 62000,
            productUnit = ProductUnit.PC
        ),
        Product(
            id = 5,
            name = "Bread",
            description = "Whole grain bread",
            image = R.drawable.ic_bread,
            price = 15000,
            productUnit = ProductUnit.PC
        ),
        Product(
            id = 6,
            name = "Cheese",
            description = "Cheddar cheese",
            image = R.drawable.ic_cheese,
            price = 500000,
            productUnit = ProductUnit.PC
        ),
        Product(
            id = 7,
            name = "Tomato",
            description = "Juicy red tomatoes",
            image = R.drawable.ic_tomato,
            price = 90000,
            productUnit = ProductUnit.KG
        ),
        Product(
            id = 8,
            name = "Chicken",
            description = "Fresh chicken meat",
            image = R.drawable.ic_chicken,
            price = 243000,
            productUnit = ProductUnit.KG
        ),
        Product(
            id = 9,
            name = "Orange Juice",
            description = "Freshly squeezed orange juice",
            image = R.drawable.ic_orange_juice,
            price = 58000,
            productUnit = ProductUnit.PC
        ),
        Product(
            id = 10,
            name = "Eggs",
            description = "Organic eggs",
            image = R.drawable.ic_eggs,
            price = 70000,
            productUnit = ProductUnit.PC
        ),
        Product(
            id = 11,
            name = "Yogurt",
            description = "Greek yogurt",
            image = R.drawable.ic_yogurt,
            price = 11000,
            productUnit = ProductUnit.PC
        ),
        Product(
            id = 12,
            name = "Spinach",
            description = "Fresh spinach leaves",
            image = R.drawable.ic_spinach,
            price = 120000,
            productUnit = ProductUnit.KG
        ),
        Product(
            id = 13,
            name = "Pasta",
            description = "Italian pasta",
            image = R.drawable.ic_pasta,
            price = 33000,
            productUnit = ProductUnit.PC
        ),
        Product(
            id = 14,
            name = "Rice",
            description = "Basmati rice",
            image = R.drawable.ic_rice,
            price = 58000,
            productUnit = ProductUnit.KG
        ),
        Product(
            id = 15,
            name = "Coffee",
            description = "Ground coffee beans",
            image = R.drawable.ic_coffee,
            price = 120000,
            productUnit = ProductUnit.PC
        ),
        Product(
            id = 16,
            name = "Tea",
            description = "Green tea",
            image = R.drawable.ic_tea,
            price = 45000,
            productUnit = ProductUnit.PC
        ),
        Product(
            id = 17,
            name = "Sugar",
            description = "White sugar",
            image = R.drawable.ic_sugar,
            price = 60000,
            productUnit = ProductUnit.KG
        ),
        Product(
            id = 18,
            name = "Salt",
            description = "Table salt",
            image = R.drawable.ic_salt,
            price = 10000,
            productUnit = ProductUnit.KG,
        ),
        Product(
            id = 19,
            name = "Butter",
            description = "Creamy butter",
            image = R.drawable.ic_butter,
            price = 34000,
            productUnit = ProductUnit.PC
        ),
        Product(
            id = 20,
            name = "Peanut Butter",
            description = "Crunchy peanut butter",
            image = R.drawable.ic_peanut_butter,
            price = 220000,
            productUnit = ProductUnit.PC
        )
    )
}