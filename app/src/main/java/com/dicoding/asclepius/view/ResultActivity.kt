package com.dicoding.asclepius.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.R
import com.dicoding.asclepius.adapter.NewsAdapter
import com.dicoding.asclepius.data.remote.response.ArticlesItem
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.utils.FIRST_LABEL_RESULT_EXTRA
import com.dicoding.asclepius.utils.FIRST_SCORE_RESULT_EXTRA
import com.dicoding.asclepius.utils.IMAGE_URI_EXTRA
import com.dicoding.asclepius.utils.SECOND_LABEL_RESULT_EXTRA
import com.dicoding.asclepius.utils.SECOND_SCORE_RESULT_EXTRA
import com.dicoding.asclepius.viewmodel.NewsViewModel

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private val newsViewModel by viewModels<NewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAppBar()
        setupRecyclerView()

        newsViewModel.listNews.observe(this) {
            if (it != null) {
                val filteredData = it.filter { articlesItem ->
                    articlesItem.author != null
                }
                setListNewsData(filteredData)
            }
        }

        newsViewModel.loader.observe(this) {
            showLoader(it)
        }

        val cancerImage = intent.getStringExtra(IMAGE_URI_EXTRA)
        val firstLabelResult = intent.getStringExtra(FIRST_LABEL_RESULT_EXTRA)
        val firstScoreResult = intent.getStringExtra(FIRST_SCORE_RESULT_EXTRA)
        val secondLabelResult = intent.getStringExtra(SECOND_LABEL_RESULT_EXTRA)
        val secondScoreResult = intent.getStringExtra(SECOND_SCORE_RESULT_EXTRA)

        with(binding) {
            resultImage.setImageURI(cancerImage?.toUri())
            leftCardLabel.text = firstLabelResult
            leftCardScore.text = firstScoreResult
            rightCardLabel.text = secondLabelResult
            rightCardScore.text = secondScoreResult
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

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvNews.layoutManager = layoutManager
    }

    private fun setListNewsData(news: List<ArticlesItem>) {
        val adapter = NewsAdapter()
        adapter.submitList(news)
        binding.rvNews.adapter = adapter
    }

    private fun showLoader(isLoading: Boolean) {
        binding.loader.root.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
    }
}