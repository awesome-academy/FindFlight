package com.sun.findflight.ui.flightinfodetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.findflight.base.BaseFragment
import com.sun.findflight.data.model.FlightDetail
import com.sun.findflight.data.model.Segment
import com.sun.findflight.databinding.FragmentFlightInfoDetailBinding
import com.sun.findflight.ui.flightinfodetail.adapter.SegmentAdapter
import com.sun.findflight.ui.offerflightslist.OfferFlightsListFragment
import com.sun.findflight.utils.hide

class FlightInfoDetailFragment : BaseFragment<FragmentFlightInfoDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFlightInfoDetailBinding =
        FragmentFlightInfoDetailBinding::inflate
    private val segmentAdapter by lazy {
        SegmentAdapter(
            mutableListOf(),
            this::itemSegmentClick
        )
    }

    override fun initComponents() {
        viewBinding.recyclerFlightsInfo.adapter = segmentAdapter
        viewBinding.progressBarFlightInfo.hide()
    }

    override fun initEvents() {
    }

    override fun initData() {
    }

    private fun itemSegmentClick(segment: Segment) = Unit
}
