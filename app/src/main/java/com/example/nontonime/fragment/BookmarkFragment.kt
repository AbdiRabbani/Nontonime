package com.example.nontonime.fragment

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.nontonime.adapter.GridAdapter
import com.example.nontonime.adapter.MovieAdapter
import com.example.nontonime.adapter.PopularAdapter
import com.example.nontonime.adapter.SwipeToDelete
import com.example.nontonime.databinding.FragmentBookmarkBinding
import com.example.nontonime.response.DataResponseItem
import com.example.nontonime.viewmodel.MainViewModel

class BookmarkFragment() : Fragment() {

    private var _binding : FragmentBookmarkBinding? = null
    private val binding get() = _binding as FragmentBookmarkBinding

    private var _viewModel: MainViewModel? = null
    private val viewModel get() = _viewModel as MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reloadLayout.setOnRefreshListener {
            showData()
            binding.reloadLayout.isRefreshing = false
        }
        showData()
    }

    private fun showData() {
        viewModel.apply {
            getAllBookmarkedMovie().observe(viewLifecycleOwner) {data ->
                val gridAdapter = GridAdapter()

                data.let {
                    binding.rvBookmark.apply {
                        gridAdapter.setData(data)
                        Log.i("ooooo", "showData: $data")
                        adapter =  gridAdapter
                        layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                        ItemTouchHelper(SwipeToDelete(viewModel, gridAdapter, context)).attachToRecyclerView(this)
                    }
                }
            }
        }

    }
}