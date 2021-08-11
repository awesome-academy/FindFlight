package com.sun.findflight.ui.flightfaredetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.findflight.base.BaseFragment
import com.sun.findflight.databinding.FragmentFlightFareDetailBinding

class FlightFareDetailFragment : BaseFragment<FragmentFlightFareDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFlightFareDetailBinding =
        FragmentFlightFareDetailBinding::inflate

    override fun initComponents() {}

    override fun initEvents() {}

    override fun initData() {}
}
