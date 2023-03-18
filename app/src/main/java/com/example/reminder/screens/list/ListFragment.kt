package com.example.reminder.screens.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.reminder.R
import com.example.reminder.data.local.viewmodel.ReminderViewModel
import com.example.reminder.databinding.FragmentListBinding
import com.example.reminder.screens.ValidData
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment(), SearchView.OnQueryTextListener {

    private val viewModel: ReminderViewModel by viewModels()
    private val valid: ValidData by viewModels()

    private val adapterList: ListAdapter by lazy{ ListAdapter()}

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        binding.valid = valid

        initRecyclerView()
        viewModel.getAll.observe(viewLifecycleOwner) {
            valid.checkEmptyData(it)
            adapterList.setAdapterData(it)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun initRecyclerView() {
        val recyclerView = binding.rvList
        recyclerView.adapter = adapterList
        recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recyclerView.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 300
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_all){
            confirmAllDelete()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onQueryTextSubmit(search: String?): Boolean {
        if(search != null){
            searchParametrDataBase(search)
        }
        return true
    }

    override fun onQueryTextChange(search: String?): Boolean {
        if(search != null){
            searchParametrDataBase(search)
        }
        return true
    }

    private fun searchParametrDataBase(search: String) {
        var searchSring: String = search
        searchSring = "%$searchSring%"
        viewModel.searchDB(searchSring).observe(this,Observer{
            it?.let{
                adapterList.setAdapterData(it)
            }
        })
    }
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
        val search: MenuItem = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
    }

    private fun confirmAllDelete(){
        val build = AlertDialog.Builder(requireContext())
        build.setPositiveButton("Да"){_,_ ->
            viewModel.deleteAll()
            Toast.makeText(context,"Удалено",Toast.LENGTH_SHORT).show()
        }
        build.setNegativeButton("Нет"){_,_ ->}
        build.setTitle("Очистить список ")
        build.setMessage("Действительно удалить все?")
        build.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}