package com.dexciuq.arbuz_kz.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dexciuq.arbuz_kz.R
import com.dexciuq.arbuz_kz.databinding.ActivityMainBinding
import com.dexciuq.arbuz_kz.presentation.screens.cart.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBottomNavigationView()
        collectBadgeCount()
    }

    private fun setupBottomNavigationView() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment_container
        ) as NavHostFragment

        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)
    }

    private fun collectBadgeCount() {
        lifecycleScope.launch {
            viewModel.products.collectLatest { products ->
                when (products.size) {
                    0 -> {
                        binding.bottomNavigationView.removeBadge(R.id.nav_graph_cart)
                    }

                    else -> {
                        binding.bottomNavigationView.apply {
                            getOrCreateBadge(R.id.nav_graph_cart).number = products.size
                            getOrCreateBadge(R.id.nav_graph_cart).backgroundColor = Color.RED
                        }
                    }
                }
            }
        }
    }
}