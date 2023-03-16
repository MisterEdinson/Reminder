package com.example.reminder.screens

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.reminder.R
import com.example.reminder.data.local.models.Priority
import com.example.reminder.data.local.models.ReminderData

class ValidData(application: Application) : AndroidViewModel(application) {

    val nullData:MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkNullData(reminderData: List<ReminderData>){
        nullData.value = reminderData.isEmpty()
    }

    val colorListener: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0->{(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,R.color.purple_200))}
                1->{(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,R.color.purple_500))}
                2->{(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,R.color.purple_700))}
                3->{(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,R.color.teal_200))}
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
}