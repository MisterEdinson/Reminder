package com.example.reminder.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.reminder.data.local.models.ReminderData

class RemindDiffUtil(
    private val oldList: List<ReminderData>,
    private val newList: List<ReminderData>):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id &&
                oldList[oldItemPosition].title == newList[newItemPosition].title &&
                oldList[oldItemPosition].desc == newList[newItemPosition].desc &&
                oldList[oldItemPosition].priority == newList[newItemPosition].priority
    }
}