package com.example.facebook.base


import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseListAdapter<Item>(
    diffUtils: DiffUtil.ItemCallback<Item>,
) : ListAdapter<Item, BaseHolder<Item>>(diffUtils) {


    override fun onBindViewHolder(viewHolder: BaseHolder<Item>, position: Int) {

        val item = getItem(position)

        if (item != null) {
            viewHolder.onBind(item)
        }

    }
}