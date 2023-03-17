package com.example.reminder.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.reminder.data.local.models.ReminderData

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminder_base_room ORDER BY id ASC")
    fun getAll(): LiveData<List<ReminderData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(ReminderData: ReminderData)

    @Update
    suspend fun updateRemind(reminderData: ReminderData)

    @Delete
    suspend fun deleteRemind(reminderData: ReminderData)

    @Query("DELETE FROM reminder_base_room")
    suspend fun deleteAll()

    @Query("SELECT * FROM reminder_base_room WHERE title LIKE :searchTitle")
    fun searchTitle(searchTitle: String): LiveData<List<ReminderData>>
}