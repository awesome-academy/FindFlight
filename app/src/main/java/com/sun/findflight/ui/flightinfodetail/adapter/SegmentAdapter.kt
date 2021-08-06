package com.sun.findflight.ui.flightinfodetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.findflight.R
import com.sun.findflight.base.BaseAdapter
import com.sun.findflight.base.BaseViewHolder
import com.sun.findflight.data.model.Segment
import com.sun.findflight.data.source.remote.api.ApiQuery
import com.sun.findflight.databinding.ItemFlightInfoBinding
import com.sun.findflight.utils.hide
import com.sun.findflight.utils.loadImage
import java.util.*

class SegmentAdapter(
    items: MutableList<Segment>,
    private val onItemClick: (Segment) -> Unit
) : BaseAdapter<Segment, SegmentAdapter.SegmentViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SegmentViewHolder(
            ItemFlightInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClick
        )

    class SegmentViewHolder(
        private val viewBinding: ItemFlightInfoBinding,
        onItemClick: (Segment) -> Unit,
    ) : BaseViewHolder<Segment>(viewBinding, onItemClick)
}
