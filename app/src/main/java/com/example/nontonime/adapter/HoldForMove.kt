package com.example.nontonime.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.nontonime.viewmodel.MainViewModel
import java.util.Collections

class HoldForMove(private val adapter: GridAdapter, private val viewModel: MainViewModel) :
    ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        var startPosition = viewHolder.adapterPosition
        var endPosition = target.adapterPosition

        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        TODO("Not yet implemented")
    }

}