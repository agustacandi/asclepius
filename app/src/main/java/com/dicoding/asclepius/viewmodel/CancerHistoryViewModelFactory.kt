package com.dicoding.asclepius.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.di.Injection
import com.dicoding.asclepius.repository.CancerHistoryRepository

class CancerHistoryViewModelFactory(private val cancerHistoryRepository: CancerHistoryRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CancerHistoryViewModel::class.java)) {
            return CancerHistoryViewModel(cancerHistoryRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: CancerHistoryViewModelFactory? = null
        fun getInstance(context: Context): CancerHistoryViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: CancerHistoryViewModelFactory(
                    Injection.provideCancerHistoryRepository(
                        context
                    )
                )
            }.also { instance = it }
    }
}