package com.dexciuq.arbuz_kz.presentation.screens.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dexciuq.arbuz_kz.databinding.FragmentCartBinding
import com.dexciuq.arbuz_kz.domain.model.Product
import com.dexciuq.arbuz_kz.utils.SwipeToDeleteCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment() {

    private val binding by lazy { FragmentCartBinding.inflate(layoutInflater) }
    private val viewModel: CartViewModel by viewModels()
    private lateinit var adapter: CartProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupCartProductListRecyclerView()
        collectData()
    }

    private fun setupListeners() {
        binding.clearButton.setOnClickListener {
            viewModel.clearAll()
        }

        binding.checkoutButton.setOnClickListener {
            // TODO for future
        }
    }

    private fun setupCartProductListRecyclerView() {
        adapter = CartProductListAdapter(viewModel::updateProduct)
        binding.cartProductList.adapter = adapter

        // Swipe to delete
        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val product = adapter.currentList[viewHolder.adapterPosition]
                viewModel.updateProduct(product.copy(quantity = 0))
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.cartProductList)
    }

    private fun collectData() {
        lifecycleScope.launch {
            viewModel.products.collectLatest { products ->
                adapter.submitList(products)
                setupCheckoutContainer(products)
            }
        }
    }

    private fun setupCheckoutContainer(productList: List<Product>) {
        val deliveryFee = 30000L  // 300 ₸
        val subtotal = productList.sumOf { it.quantity * it.price }
        val total = deliveryFee + subtotal

        binding.subtotal.text = subtotal.toMoney()
        binding.deliveryFee.text = deliveryFee.toMoney()
        binding.total.text = total.toMoney()
    }

    private fun Long.toMoney(): String = "${this / 100} ₸"
}