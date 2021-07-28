package com.sun.findflight.ui.searchPlace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.findflight.base.BaseAdapter
import com.sun.findflight.base.BaseViewHolder
import com.sun.findflight.data.model.Place
import com.sun.findflight.databinding.ItemPlaceBinding

class PlaceListAdapter(private val onItemClick: (Place) -> Unit, items: MutableList<Place>) :
    BaseAdapter<Place, PlaceListAdapter.PlaceListViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceListViewHolder =
        PlaceListViewHolder(
            ItemPlaceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), onItemClick
        )

    class PlaceListViewHolder(
        private val viewBinding: ItemPlaceBinding,
        onItemClick: (Place) -> Unit
    ) : BaseViewHolder<Place>(viewBinding, onItemClick) {

        override fun bindData(itemData: Place) {
            super.bindData(itemData)
        }
    }
}
