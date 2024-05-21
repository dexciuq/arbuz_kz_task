package com.dexciuq.arbuz_kz.presentation.screens.cart

import androidx.recyclerview.widget.DiffUtil
import com.dexciuq.arbuz_kz.domain.model.Product

class CartProductDiffUtil : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}