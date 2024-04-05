package com.dicoding.asclepius.di

import android.content.Context
import com.dicoding.asclepius.data.local.room.CancerHistoryDatabase
import com.dicoding.asclepius.repository.CancerHistoryRepository

object Injection {
    fun provideCancerHistoryRepository(context: Context): CancerHistoryRepository {
        val database = CancerHistoryDatabase.getInstance(context)
        val dao = database.cancerHistoryDao()
        return CancerHistoryRepository.getInstance(dao)
    }
}