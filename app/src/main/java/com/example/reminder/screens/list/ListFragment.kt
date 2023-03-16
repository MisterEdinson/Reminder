package com.example.reminder.screens.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reminder.R
import com.example.reminder.data.local.viewmodel.ReminderViewModel
import com.example.reminder.screens.ValidData
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private val viewModel: ReminderViewModel by viewModels()
    private val adapterList: ListAdapter by lazy{ ListAdapter()}
    private val valid: ValidData by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val recyclerView = view.rvList
        recyclerView.adapter = adapterList
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        viewModel.getAll.observe(viewLifecycleOwner, Observer {
            valid.checkNullData(it)
            adapterList.setAdapterData(it)
        })
        valid.nullData.observe(viewLifecycleOwner, Observer {
            checkNotNull(it)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        setHasOptionsMenu(true)
        return view
    }

    private fun checkNotNull(nullData: Boolean) {
        if(nullData){
            view?.tvNoData?.visibility = View.VISIBLE
        }else{
            view?.tvNoData?.visibility = View.INVISIBLE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_all){
            confirmAllDelete()
        }
        return super.onOptionsItemSelected(item)
    }
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    private fun confirmAllDelete(){
        val build = AlertDialog.Builder(requireContext())
        build.setPositiveButton("Да"){_,_ ->
            viewModel.deleteAll()
            Toast.makeText(
                context,
                "Удалено",
                Toast.LENGTH_SHORT
            ).show()
        }
        build.setNegativeButton("Нет"){_,_ ->}
        build.setTitle("Очистить список ")
        build.setMessage("Действительно удалить все?")
        build.create().show()
    }
}