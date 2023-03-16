package com.example.reminder.data.local.room

import androidx.room.TypeConverter
import com.example.reminder.data.local.Priority

class PriorityConverter {
    @TypeConverter
    fun fromPriority(priority: Priority):String{
        return priority.name
    }
    @TypeConverter
    fun toPriority(priority: String):Priority{
        return Priority.valueOf(priority)
    }
}