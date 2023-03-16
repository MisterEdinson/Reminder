package com.example.reminder.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminder_base_room")
data class ReminderData(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String,
    var desc:String,
    var priority: Priority
)