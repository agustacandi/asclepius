package com.dicoding.asclepius.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.model.CancerHistory
import com.dicoding.asclepius.repository.CancerHistoryRepository
import kotlinx.coroutines.launch

class CancerHistoryViewModel(private val cancerHistoryRepository: CancerHistoryRepository) :
    ViewModel() {

    fun getAllCancerHistory() = cancerHistoryRepository.getAllCancerHistory()

    fun insertCancerHistory(cancerHistory: CancerHistory) {
        viewModelScope.launch {
            cancerHistoryRepository.insertCancerHistory(cancerHistory)
        }
    }
}