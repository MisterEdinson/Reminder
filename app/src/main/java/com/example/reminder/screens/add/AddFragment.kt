package com.example.reminder.screens.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.reminder.R
import com.example.reminder.data.local.models.ReminderData
import com.example.reminder.data.local.viewmodel.ReminderViewModel
import com.example.reminder.screens.ValidData
import kotlinx.android.synthetic.main.fragment_add.etUpdDesc
import kotlinx.android.synthetic.main.fragment_add.etUpdTitle
import kotlinx.android.synthetic.main.fragment_add.spUpdPriority
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private val reminderViewModel:ReminderViewModel by viewModels()
    private val ValidData:ValidData by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add, container, false)
        setHasOptionsMenu(true)
        view.spUpdPriority.onItemSelectedListener = ValidData.colorListener
        return view
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu,menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add){
            isertDataToDB()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun isertDataToDB() {
        val title = etUpdTitle.text.toString()
        val priority = spUpdPriority.selectedItem.toString()
        val description = etUpdDesc.text.toString()

        val valid = ValidData.verificationData(title,description)
        if(valid){
            val newRemind = ReminderData(
                0,
                title,
                description,
                ValidData.convertPriority(priority)
            )
            reminderViewModel.insertData(newRemind)
            Toast.makeText(context,"Добавлено :)", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(context,"Не могу добавить :(", Toast.LENGTH_SHORT).show()
        }
    }


}