package com.dicoding.asclepius.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.asclepius.model.CancerHistory

@Dao
interface CancerHistoryDao {
    @Query("SELECT * FROM CancerHistory")
    fun getAllCancerHistory(): LiveData<List<CancerHistory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCancerHistory(cancerHistory: CancerHistory)
}