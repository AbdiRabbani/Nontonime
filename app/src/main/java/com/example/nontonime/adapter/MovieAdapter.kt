package com.example.nontonime.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nontonime.R
import com.example.nontonime.activity.DetailActivity
import com.example.nontonime.databinding.ItemMovieBinding
import com.example.nontonime.response.DataResponseItem
import com.squareup.picasso.Picasso

class MovieAdapter(private val listMovie: ArrayList<DataResponseItem>) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {

            if (listMovie[position].episodeNum != null) {
                titleContent.text =
                    "${listMovie[position].animeTitle} eps ${listMovie[position].episodeNum}"
            } else {
                titleContent.text = listMovie[position].animeTitle
            }

            Picasso.get().load(listMovie[position].animeImg).placeholder(R.drawable.loading)
                .into(imgContent)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.MOVIE_DATA, listMovie[position].animeId)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount() = listMovie.size
}