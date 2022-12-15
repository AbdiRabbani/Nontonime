package com.example.nontonime.adapter

import android.content.Context
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.nontonime.viewmodel.MainViewModel

class SwipeToDelete(private val viewModel: MainViewModel, private val adapter : GridAdapter, private val context: Context) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val movie = adapter.getMovieAt(position)

        Log.i("SwipeToDelete", "onSwiped: $movie")

        viewModel.unBookmarkMovie(movie)
        Toast.makeText(context, "${movie.animeTitle} was deleted", Toast.LENGTH_SHORT).show()
    }
}