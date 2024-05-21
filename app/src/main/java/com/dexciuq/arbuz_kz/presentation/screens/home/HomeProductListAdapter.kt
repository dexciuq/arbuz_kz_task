package com.dexciuq.arbuz_kz.presentation.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dexciuq.arbuz_kz.databinding.ItemProductBinding
import com.dexciuq.arbuz_kz.domain.model.Product
import com.dexciuq.arbuz_kz.utils.hide
import com.dexciuq.arbuz_kz.utils.show

class HomeProductListAdapter(
    private val onUpdateQuantityClick: (Product) -> Unit,
) : ListAdapter<Product, HomeProductListAdapter.ViewHolder>(HomeProductDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            with(binding) {
                name.text = product.name
                price.text = product.getPrice()
                quantity.text = product.quantity.toString()
                image.setImageResource(product.image)

                if (product.quantity == 0) {
                    addToCardContainer.show()
                    quantityContainer.hide()
                } else {
                    addToCardContainer.hide()
                    quantityContainer.show()
                }

                addToCardContainer.setOnClickListener {
                    addToCardContainer.hide()
                    quantityContainer.show()
                    plus.performClick()
                }

                plus.setOnClickListener {
                    onUpdateQuantityClick(product.copy(quantity = product.quantity + 1))
                }

                minus.setOnClickListener {
                    onUpdateQuantityClick(product.copy(quantity = product.quantity - 1))
                }
            }
        }
    }
}