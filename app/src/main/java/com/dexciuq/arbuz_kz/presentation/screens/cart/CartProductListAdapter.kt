package com.dexciuq.arbuz_kz.presentation.screens.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dexciuq.arbuz_kz.databinding.ItemCartProductBinding
import com.dexciuq.arbuz_kz.domain.model.Product

class CartProductListAdapter(
    private val onUpdateQuantityClick: (Product) -> Unit,
) : ListAdapter<Product, CartProductListAdapter.ViewHolder>(CartProductDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCartProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(
        private val binding: ItemCartProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            with(binding) {
                name.text = product.name
                price.text = product.getPrice()
                quantity.text = product.quantity.toString()
                image.setImageResource(product.image)

                plus.setOnClickListener {
                    product.quantity++
                    onUpdateQuantityClick(product)
                    quantity.text = product.quantity.toString()
                }

                minus.setOnClickListener {
                    product.quantity--
                    onUpdateQuantityClick(product)
                    quantity.text = product.quantity.toString()
                }
            }
        }
    }
}