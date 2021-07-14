package com.sun.findflight.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, V : BaseViewHolder<T>>(
    private val items: MutableList<T>
) : RecyclerView.Adapter<V>() {

    override fun onBindViewHolder(holder: V, position: Int) {
        getItem(position)?.let { holder.bindData(it) }
    }

    override fun getItemCount() = items.size

    private fun getItem(position: Int): T? =
        if (position in 0 until itemCount) items[position] else null

    open fun updateData(newData: MutableList<T>) {
        items.apply {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }
}
