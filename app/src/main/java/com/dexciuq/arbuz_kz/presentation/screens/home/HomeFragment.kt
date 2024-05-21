package com.dexciuq.arbuz_kz.presentation.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dexciuq.arbuz_kz.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomeProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProductListSection()
        collectData()
    }

    private fun setupProductListSection() {
        adapter = HomeProductListAdapter(viewModel::updateProduct)
        binding.productList.adapter = adapter
    }

    private fun collectData() {
        lifecycleScope.launch {
            viewModel.products.collectLatest { products ->
                adapter.submitList(products)
            }
        }
    }
}