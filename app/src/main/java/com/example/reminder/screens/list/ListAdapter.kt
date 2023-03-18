package com.example.reminder.screens.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.reminder.R
import com.example.reminder.data.local.models.Priority
import com.example.reminder.data.local.models.ReminderData
import com.example.reminder.diffUtil.RemindDiffUtil
import kotlinx.android.synthetic.main.item_remind.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.AdapterViewHolder>() {

    var remindList = emptyList<ReminderData>()


    class AdapterViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_remind, parent, false)
        return AdapterViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val item = remindList[position]
        holder.itemView.apply {
            tvTitle.text = item.title
            tvDesc.text = item.desc

            itemReminderBlock.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(item)
                findNavController().navigate(action)
            }

            val priority = item.priority
            when (priority) {
                Priority.HIGHT -> colorPriority.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.srong_hight
                    )
                )
                Priority.MEDIUM -> colorPriority.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.hight
                    )
                )
                Priority.NEED -> colorPriority.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.medium
                    )
                )
                Priority.LOW -> colorPriority.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.light
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return remindList.size
    }

    fun setAdapterData(list: List<ReminderData>) {
        val remindDiffUtil = RemindDiffUtil(remindList, list)
        val remindDiffUtilResult = DiffUtil.calculateDiff(remindDiffUtil)
        remindList = list
        remindDiffUtilResult.dispatchUpdatesTo(this)
        //notifyDataSetChanged()
    }


}

