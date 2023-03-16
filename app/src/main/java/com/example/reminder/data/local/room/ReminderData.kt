package com.example.reminder.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.reminder.data.local.Priority

@Entity(tableName = "reminder_base_room")
data class ReminderData(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String,
    var desc:String,
    var priority: Priority
)