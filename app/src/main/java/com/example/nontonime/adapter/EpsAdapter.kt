package com.example.nontonime.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nontonime.activity.DetailActivity
import com.example.nontonime.activity.WatchActivity
import com.example.nontonime.databinding.ItemEpsBinding
import com.example.nontonime.response.EpisodeListItem

class EpsAdapter(private val listView: ArrayList<EpisodeListItem?>?) :
    RecyclerView.Adapter<EpsAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemEpsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemEpsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            tvEps.text = "Eps ${listView?.get(position)?.episodeNum}"
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, WatchActivity::class.java)
            intent.putExtra(WatchActivity.WATCH_DATA, listView?.get(position)?.episodeId)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listView!!.size

}