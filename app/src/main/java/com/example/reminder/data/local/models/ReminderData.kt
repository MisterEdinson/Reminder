package com.example.reminder.data.local.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "reminder_base_room")
@Parcelize
data class ReminderData(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String,
    var desc:String,
    var priority: Priority
) : Parcelable