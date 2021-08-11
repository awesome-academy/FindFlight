package com.sun.findflight.ui.flightdetail.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sun.findflight.data.model.FlightDetail
import com.sun.findflight.ui.flightinfodetail.FlightInfoDetailFragment
import com.sun.findflight.ui.offerflightslist.OfferFlightsListFragment

class FlightDetailStateAdapter(
    activity: FragmentActivity,
    private val flightDetail: FlightDetail?
) : FragmentStateAdapter(activity) {

    private val fragments = arrayOf(
        FlightInfoDetailFragment(),
        Fragment()
    )

    override fun createFragment(position: Int) = fragments[position].apply {
        arguments = bundleOf(OfferFlightsListFragment.DATA_FLIGHT_DETAIL to flightDetail)
    }

    override fun getItemCount() = FRAGMENT_COUNT

    companion object {
        const val FRAGMENT_COUNT = 2
    }
}
