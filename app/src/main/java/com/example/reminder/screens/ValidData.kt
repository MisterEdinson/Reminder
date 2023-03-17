package com.example.reminder.screens

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.reminder.R
import com.example.reminder.data.local.models.Priority
import com.example.reminder.data.local.models.ReminderData

class ValidData(application: Application) : AndroidViewModel(application), LifecycleOwner {

    val emptyData:MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkEmptyData(reminderData: List<ReminderData>){
        emptyData.value = reminderData.isEmpty()
    }

    val colorListener: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0->{(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,R.color.srong_hight))}
                1->{(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,R.color.hight))}
                2->{(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,R.color.medium))}
                3->{(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,R.color.light))}
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }

    fun verificationData(title:String,description:String): Boolean{
        return if(TextUtils.isEmpty(title) || TextUtils.isEmpty(description)){
            false
        }else !(title.isEmpty() || description.isEmpty())
    }

    fun convertPriority(priority: String): Priority {
        return when(priority){
            "Очень важно" -> {
                Priority.HIGHT}
            "Важно" -> {
                Priority.MEDIUM}
            "Нужно" -> {
                Priority.NEED}
            "Желательно" -> {
                Priority.LOW}
            else -> Priority.LOW
        }
    }

    fun convertPriority(priority: Priority):Int{
        return when(priority){
            Priority.HIGHT -> 0
            Priority.MEDIUM -> 1
            Priority.NEED -> 2
            Priority.LOW -> 3
        }
    }

    override fun getLifecycle(): Lifecycle {
        TODO("Not yet implemented")
    }
}