package com.example.nontonime.activity

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nontonime.adapter.MovieAdapter
import com.example.nontonime.databinding.ActivitySearchBinding
import com.example.nontonime.response.DataResponseItem
import com.example.nontonime.viewmodel.MainViewModel

class SearchActivity : AppCompatActivity() {

    private var _viewModel: MainViewModel? = null
    private val viewModel get() = _viewModel as MainViewModel

    private var _binding: ActivitySearchBinding? = null
    private val binding get() = _binding as ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText.let {
                    viewModel.apply {
                        getDataSearch(newText)
                        dataSearchResponse.observe(this@SearchActivity) { showData(it as ArrayList<DataResponseItem>) }
                        isError.observe(this@SearchActivity) { showError(it) }
                    }
                }
                return true
            }

        })

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun showError(error: Throwable?) {
        Log.e("HomeFragment", "showError: $error")
    }

    private fun showData(data: ArrayList<DataResponseItem>) {
        binding.rvSearch.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = MovieAdapter(data)
        }
    }

}