package com.example.reminder.data.local.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.reminder.data.local.models.ReminderData
import com.example.reminder.data.local.room.ReminderDB
import com.example.reminder.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReminderViewModel(application: Application) : AndroidViewModel(application) {
    private val reminderDao = ReminderDB.getDataBase(application).ReminderDao()
    private val repository: Repository

    val getAll: LiveData<List<ReminderData>>

    init {
        repository = Repository(reminderDao)
        getAll = repository.getAll
    }

    fun insertData(reminderData: ReminderData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(reminderData)
        }
    }
    fun updateIt(reminderData: ReminderData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItem(reminderData)
        }
    }
    fun deleteIt(reminderData: ReminderData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(reminderData)
        }
    }
    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}