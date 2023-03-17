package com.example.reminder.screens

import android.view.View
import androidx.navigation.findNavController
import com.example.reminder.R
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapter {
    companion object{
        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateAddFragment(view: FloatingActionButton, navigate:Boolean){
            view.setOnClickListener{
                if(navigate){
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
                }
            }
        }
        @BindingAdapter("android:checkNullData")
        @JvmStatic
        fun checkNullData(view: View, checkNullData: MutableLiveData<Boolean>){
            when(checkNullData.value){
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
                else -> {view.visibility = View.INVISIBLE}
            }
        }
    }
}