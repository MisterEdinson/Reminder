package com.example.reminder.data.local.room

import android.content.Context
import androidx.room.*

@Database(entities = [ReminderData::class], version = 1, exportSchema = false)
@TypeConverters(PriorityConverter::class)
abstract class ReminderDB : RoomDatabase() {

    abstract fun ReminderDao(): ReminderDao
    companion object {
        @Volatile
        private var INSTANCE: ReminderDB? = null

        fun getDataBase(context: Context): ReminderDB {
            val tmpInst = INSTANCE
            if (tmpInst != null) {
                return tmpInst
            }
            synchronized(this) {
                val inst = Room.databaseBuilder(
                    context.applicationContext,
                    ReminderDB::class.java,
                    "reminder_base_room"
                ).build()
                INSTANCE = inst
                return inst
            }
        }
    }

}