package com.sun.findflight.ui.flightdetail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sun.findflight.data.model.FlightDetail

class FlightDetailStateAdapter(
    activity: FragmentActivity,
    private val flightDetail: FlightDetail?
) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return Fragment()
    }

    override fun getItemCount() = FRAGMENT_COUNT

    companion object {
        const val FRAGMENT_COUNT = 2
    }
}
