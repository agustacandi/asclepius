package com.dicoding.asclepius.repository

import androidx.lifecycle.LiveData
import com.dicoding.asclepius.data.local.room.CancerHistoryDao
import com.dicoding.asclepius.model.CancerHistory

class CancerHistoryRepository private constructor(
    private val cancerHistoryDao: CancerHistoryDao,
) {

    fun getAllCancerHistory(): LiveData<List<CancerHistory>> {
        return cancerHistoryDao.getAllCancerHistory()
    }

    suspend fun insertCancerHistory(cancerHistory: CancerHistory) {
        return cancerHistoryDao.insertCancerHistory(cancerHistory)
    }

    companion object {
        @Volatile
        private var instance: CancerHistoryRepository? = null
        fun getInstance(
            cancerHistoryDao: CancerHistoryDao,
        ): CancerHistoryRepository = instance ?: synchronized(this) {
            instance ?: CancerHistoryRepository(cancerHistoryDao).also {
                instance = it
            }
        }
    }
}