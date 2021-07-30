package com.sun.findflight.ui.basicflightslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.findflight.base.BaseAdapter
import com.sun.findflight.base.BaseViewHolder
import com.sun.findflight.data.model.Flight
import com.sun.findflight.databinding.ItemFlightBinding

class BasicFlightListAdapter(
    items: MutableList<Flight>,
    private val onItemClick: (Flight) -> Unit,
) : BaseAdapter<Flight, BasicFlightListAdapter.FlightListViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightListViewHolder =
        FlightListViewHolder(
            ItemFlightBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), onItemClick
        )

    class FlightListViewHolder(
        private val viewBinding: ItemFlightBinding,
        onItemClick: (Flight) -> Unit
    ) : BaseViewHolder<Flight>(viewBinding, onItemClick) {

        override fun bindData(itemData: Flight) {
            super.bindData(itemData)
        }
    }
}
