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
import com.example.nontonime.adapter.GridAdapter
import com.example.nontonime.adapter.MovieAdapter
import com.example.nontonime.adapter.PopularAdapter
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
            showAll()
            binding.reloadLayout.isRefreshing = false
        }
        showAll()
    }

    private fun showAll() {

        viewModel.apply {
            getAllBookmarkedMovie().observe(viewLifecycleOwner) {data ->
                data?.let {
                    Log.d("BookmarkFragment", "showAll: $data")
                    binding.rvBookmark.apply {
                        adapter =  GridAdapter(data as ArrayList<DataResponseItem>)
                        layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                    }
                }
            }
        }

    }
}