package com.sun.findflight.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T>(
    binding: ViewBinding,
    onItemClick: (T) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var itemData: T? = null

    init {
        binding.root.setOnClickListener {
            itemData?.let { onItemClick(it) }
        }
    }

    open fun bindData(itemData: T) {
        this.itemData = itemData
    }
}
