package com.example.nontonime.activity

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nontonime.R
import com.example.nontonime.adapter.EpsAdapter
import com.example.nontonime.databinding.ActivityDetailBinding
import com.example.nontonime.response.DataResponseItem
import com.example.nontonime.response.DetailResponseItem
import com.example.nontonime.viewmodel.MainViewModel
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    private var _viewModel: MainViewModel? = null
    private val viewModel get() = _viewModel as MainViewModel

    private var _data: DataResponseItem? = null
    private val data get() = _data as DataResponseItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _data = intent.getParcelableExtra(MOVIE_DATA)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        _viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val idMovie = data.animeId
        val releaseDate = data.releasedDate

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

        bookmarkBtn()

    }

    private fun bookmarkBtn() {
        binding.btnBookmark.setOnClickListener {
            viewModel.isMovieBookmarked(data)
                .observe(this@DetailActivity) { isBookmarked ->
                        binding.btnBookmark.apply {
                            if (isBookmarked) {
                                setImageResource(R.drawable.ic_bookmark)
                            } else {
                                setImageResource(R.drawable.ic_bookmark_border)
                            }

                            var message: String
                            setOnClickListener {
                                message = if (isBookmarked) {
                                    viewModel.unBookmarkMovie(data)
                                    context.getString(R.string.txt_bookmark_removed)

                                } else {
                                    viewModel.bookmarkMovie(data)
                                    context.getString(R.string.txt_bookmark_added)
                                }
                                Toast.makeText(this@DetailActivity, message, Toast.LENGTH_SHORT).show()
                            }
                        }
                }
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
            layoutManager = GridLayoutManager(context,3, GridLayoutManager.VERTICAL, false )
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