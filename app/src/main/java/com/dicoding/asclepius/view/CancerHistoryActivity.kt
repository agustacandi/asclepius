package com.dicoding.asclepius.view

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.R
import com.dicoding.asclepius.adapter.CancerHistoryAdapter
import com.dicoding.asclepius.databinding.ActivityCancerHistoryBinding
import com.dicoding.asclepius.viewmodel.CancerHistoryViewModel
import com.dicoding.asclepius.viewmodel.CancerHistoryViewModelFactory

class CancerHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCancerHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCancerHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cancerHistoryViewModelFactory = CancerHistoryViewModelFactory.getInstance(this)
        val cancerHistoryViewModel: CancerHistoryViewModel by viewModels {
            cancerHistoryViewModelFactory
        }

        setupAppBar()

        cancerHistoryViewModel.getAllCancerHistory().observe(this) {
            if (it != null) {
                binding.rvNews.layoutManager = LinearLayoutManager(this)
                val adapter = CancerHistoryAdapter(it, this)
                binding.rvNews.adapter = adapter
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> false
        }
    }

    private fun setupAppBar() {
        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24)
        supportActionBar?.elevation = 0f
    }
}