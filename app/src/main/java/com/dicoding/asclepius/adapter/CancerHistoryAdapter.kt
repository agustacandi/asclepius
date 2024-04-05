package com.dicoding.asclepius.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ItemCancerHistoryBinding
import com.dicoding.asclepius.model.CancerHistory

class CancerHistoryAdapter(
    private val listCancerHistory: List<CancerHistory>,
    private val context: Context
) : RecyclerView.Adapter<CancerHistoryAdapter.ListViewHolder>() {
    class ListViewHolder(val binding: ItemCancerHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemCancerHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listCancerHistory.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val cancerHistory = listCancerHistory[position]
        with(holder.binding) {
            ivCancerHistory.load(cancerHistory.image) {
                placeholder(ColorDrawable(Color.LTGRAY))
            }
            with(context.resources) {
                tvCancerLabel.text = getString(R.string.cancer_history_label, cancerHistory.label)
                tvCancerScore.text =
                    getString(R.string.cancer_history_score, cancerHistory.confidenceScore)
            }
        }
    }
}