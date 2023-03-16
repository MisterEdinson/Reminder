package com.example.reminder.data.repository

import androidx.lifecycle.LiveData
import com.example.reminder.data.local.room.ReminderDao
import com.example.reminder.data.local.models.ReminderData

class Repository(private val reminderDao: ReminderDao) {

    val getAll: LiveData<List<ReminderData>> = reminderDao.getAll()

    suspend fun insertData(reminderData: ReminderData){
        reminderDao.insertData(reminderData)
    }
}