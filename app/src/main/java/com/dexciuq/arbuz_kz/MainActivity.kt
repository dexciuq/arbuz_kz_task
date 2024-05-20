package com.dexciuq.arbuz_kz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dexciuq.arbuz_kz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}