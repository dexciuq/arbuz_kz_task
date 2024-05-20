package com.dexciuq.arbuz_kz.data.mapper

import com.dexciuq.arbuz_kz.data.model.ProductEntity
import com.dexciuq.arbuz_kz.domain.model.Product
import com.dexciuq.arbuz_kz.domain.model.ProductUnit

fun ProductEntity.toDomain(): Product = Product(
    id = id,
    name = name,
    description = description,
    image = image,
    price = price,
    productUnit = ProductUnit.valueOf(productUnit),
    quantity = quantity,
)

fun Product.toProductEntity() = ProductEntity(
    id = id,
    name = name,
    description = description,
    image = image,
    price = price,
    productUnit = productUnit.name,
    quantity = quantity,
)

fun List<ProductEntity>.toDomain() = map(ProductEntity::toDomain)

fun List<Product>.toProductEntity() = map(Product::toProductEntity)