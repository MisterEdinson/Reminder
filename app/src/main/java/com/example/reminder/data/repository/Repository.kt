package com.example.reminder.data.repository

import androidx.lifecycle.LiveData
import com.example.reminder.data.local.room.ReminderDao
import com.example.reminder.data.local.models.ReminderData

class Repository(private val reminderDao: ReminderDao) {

    val getAll: LiveData<List<ReminderData>> = reminderDao.getAll()
    val sortHigh : LiveData<List<ReminderData>> = reminderDao.sortedHighPriority()
    val sortLow : LiveData<List<ReminderData>> = reminderDao.sortedLowPriority()

    suspend fun insertData(reminderData: ReminderData){
        reminderDao.insertData(reminderData)
    }

    suspend fun updateItem(reminderData: ReminderData){
        reminderDao.updateRemind(reminderData)
    }
    suspend fun deleteItem(reminderData: ReminderData){
        reminderDao.deleteRemind(reminderData)
    }

    suspend fun deleteAll(){
        reminderDao.deleteAll()
    }

    fun searchTitle(searchTitle: String):LiveData<List<ReminderData>>{
        return reminderDao.searchTitle(searchTitle)
    }
}