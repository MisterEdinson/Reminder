package com.example.reminder.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.reminder.data.local.models.ReminderData

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminder_base_room ORDER BY id ASC")
    fun getAll(): LiveData<List<ReminderData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(ReminderData: ReminderData)
}