package com.example.nontonime.activity

import android.app.Activity
import android.app.DownloadManager
import android.app.Instrumentation.ActivityResult
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nontonime.R
import com.example.nontonime.adapter.EpsAdapter
import com.example.nontonime.databinding.ActivityDetailBinding
import com.example.nontonime.response.DetailResponseItem
import com.example.nontonime.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import java.io.File

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    private var _viewModel: MainViewModel? = null
    private val viewModel get() = _viewModel as MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        _viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val idMovie = intent.extras?.getString(MOVIE_DATA)
        Log.i("idddddddd", "${intent.extras?.getString(MOVIE_DATA)}")


        viewModel.apply {
            getDataDetail(idMovie)
            dataDetailResponse.observe(this@DetailActivity) {
                showData(it)
            }
            isError.observe(this@DetailActivity) { showError(it) }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

    }

    private fun showData(data: DetailResponseItem) {

        binding.apply {
            tvTitle.text = data.animeTitle
            tvRelease.text = data.releasedDate
            tvSynopsis.text = data.synopsis
            tvStatus.text = data.status
            tvGenres.text = data.genres.toString().replace("[","").replace("]", "")
        }

        Picasso.get().load(data.animeImg).placeholder(R.drawable.loading).into(binding.imgHeader)
        Picasso.get().load(data.animeImg).placeholder(R.drawable.loading).into(binding.imgContent)

        binding.rvEps.apply {
            layoutManager = GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false )
            adapter = EpsAdapter(data.episodesList)
        }
    }

    private fun showError(error: Throwable?) {
        Log.e("DetailActivity", "showError : $error")
    }


    companion object {
        const val MOVIE_DATA = "Movie"
    }
}