package com.example.nontonime.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nontonime.adapter.GridAdapter
import com.example.nontonime.adapter.MovieAdapter
import com.example.nontonime.adapter.PopularAdapter
import com.example.nontonime.databinding.FragmentHomeBinding
import com.example.nontonime.response.DataResponseItem
import com.example.nontonime.viewmodel.MainViewModel

class HomeFragment : Fragment() {


    private var _viewModel: MainViewModel? = null
    private val viewModel get() = _viewModel as MainViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.reloadLayout.setOnRefreshListener {
            showAll()
            binding.reloadLayout.isRefreshing = false
        }

        showAll()
    }

    private fun showAll() {

        viewModel.apply {
            getDataPopular()
            dataResponse.observe(viewLifecycleOwner) { showData(it as ArrayList<DataResponseItem>) }
            isError.observe(viewLifecycleOwner) { showError(it) }
        }

        viewModel.apply {
            getDataAll()
            dataMovieResponse.observe(viewLifecycleOwner) { showDataAll(it as ArrayList<DataResponseItem>) }
            isError.observe(viewLifecycleOwner) { showError(it) }
        }

        viewModel.apply {
            getDataRecent()
            dataRecentResponse.observe(viewLifecycleOwner) { showDataRecent(it as ArrayList<DataResponseItem>) }
            isError.observe(viewLifecycleOwner) { showError(it) }
        }

        viewModel.apply {
            getDataAiring()
            dataAiringResponse.observe(viewLifecycleOwner) { showDataAiring(it as ArrayList<DataResponseItem>) }
            isError.observe(viewLifecycleOwner) { showError(it) }
        }

    }

    private fun showError(error: Throwable?) {
        Log.e("HomeFragment", "showError: $error")
    }

    private fun showDataAiring(data: ArrayList<DataResponseItem>) {
        binding.rvTopAiring.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = MovieAdapter(data)
        }
    }

    private fun showDataAll(data: ArrayList<DataResponseItem>) {
        binding.rvAll.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = GridAdapter(data)
        }
    }

    private fun showDataRecent(data: ArrayList<DataResponseItem>) {
        binding.rvRecentRelease.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = MovieAdapter(data)
        }
    }

    private fun showData(data: ArrayList<DataResponseItem>) {
        binding.rvPopular.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = PopularAdapter(data)
        }
    }
}