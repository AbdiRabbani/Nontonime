package com.example.nontonime.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nontonime.R
import com.example.nontonime.activity.DetailActivity
import com.example.nontonime.databinding.GridItemMovieBinding
import com.example.nontonime.databinding.ItemPopularBinding
import com.example.nontonime.response.DataResponseItem
import com.squareup.picasso.Picasso

class GridAdapter() :
    RecyclerView.Adapter<GridAdapter.MyViewHolder>() {

    private val listMovie: ArrayList<DataResponseItem> = arrayListOf()

    class MyViewHolder(val binding: GridItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        GridItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            titleContent.text = listMovie[position].animeTitle
            releaseDate.text = listMovie[position].releasedDate

            Picasso.get().load(listMovie[position].animeImg).placeholder(R.drawable.loading).into(imgContent)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.MOVIE_DATA, listMovie[position])
            it.context.startActivity(intent)
        }
    }

    fun getMovieAt(position: Int) = listMovie[position]

    override fun getItemCount() = listMovie.size

    fun setData(data : List<DataResponseItem>?) {
        data?.let {
            val diffCallback = Diffcallback(listMovie, data)
            val diffCallbackResult = DiffUtil.calculateDiff(diffCallback)
            Log.i("pppp", "setData: $data")
            listMovie.clear()
            listMovie.addAll(data)
            Log.i("pppp", "setData: $data")
            diffCallbackResult.dispatchUpdatesTo(this)
        }
    }
}