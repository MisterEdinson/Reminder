package com.example.reminder.screens.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.reminder.R
import com.example.reminder.data.local.models.Priority
import com.example.reminder.data.local.models.ReminderData
import com.example.reminder.data.local.viewmodel.ReminderViewModel
import com.example.reminder.screens.ValidData
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.item_remind.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private val valid:ValidData by viewModels()
    private val viewModel: ReminderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        setHasOptionsMenu(true)
        view.etUpdTitle.setText(args.currentItem.title)
        view.etUpdDesc.setText(args.currentItem.desc)
        view.spUpdPriority.setSelection(valid.convertPriority(args.currentItem.priority))
        view.spUpdPriority.onItemSelectedListener = valid.colorListener
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_update){
            updateItem()
        }else if(item.itemId == R.id.menu_del){
            confirmDelItem()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun confirmDelItem(){
        val build = AlertDialog.Builder(requireContext())
        build.setPositiveButton("Да"){_,_ ->
            viewModel.deleteIt(args.currentItem)
            Toast.makeText(
                context,
                "Удалено: ${args.currentItem.title}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        build.setNegativeButton("Нет"){_,_ ->}
        build.setTitle("Удаление ${args.currentItem.title}")
        build.setMessage("Действительно удалить ${args.currentItem.title}?")
        build.create().show()
    }
    private fun updateItem(){
        val title = etUpdTitle.text.toString()
        val desc = etUpdDesc.text.toString()
        var prioritySelect = spUpdPriority.selectedItem.toString()

        val validation = valid.verificationData(title,desc)
        if(validation){
            val updateItem = ReminderData(
                args.currentItem.id,
                title,
                desc,
                valid.convertPriority(prioritySelect)
            )
            viewModel.updateIt(updateItem)
            Toast.makeText(context,"Задачу обновили :)",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(context,"Не получилось обновить :(",Toast.LENGTH_SHORT).show()
        }
    }
}