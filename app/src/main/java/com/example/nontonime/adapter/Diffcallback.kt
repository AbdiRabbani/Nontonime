package com.example.nontonime.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.nontonime.response.DataResponseItem

class Diffcallback(private val oldList: List<DataResponseItem>, private val newList: List<DataResponseItem>) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldData = oldList[oldItemPosition]
        val newData = newList[newItemPosition]

        return oldData.animeId == newData.animeId
                && oldData.animeImg == newData.animeImg
                && oldData.animeTitle == newData.animeTitle
                && oldData.releasedDate == newData.releasedDate
    }
}