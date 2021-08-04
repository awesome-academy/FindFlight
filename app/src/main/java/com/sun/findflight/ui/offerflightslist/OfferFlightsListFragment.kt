package com.sun.findflight.ui.offerflightslist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.findflight.base.BaseFragment
import com.sun.findflight.data.model.FlightDetail
import com.sun.findflight.databinding.FragmentOfferFlightsListBinding

class OfferFlightsListFragment :
    BaseFragment<FragmentOfferFlightsListBinding>(),
    OfferFlightsListContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOfferFlightsListBinding =
        FragmentOfferFlightsListBinding::inflate

    override fun initComponents() {
    }

    override fun initEvents() {
    }

    override fun initData() {
    }

    override fun showOfferFlights(flights: List<FlightDetail>) {
    }

    override fun showMessage(messageRes: Int) {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    private fun itemFlightDetailClick(flightDetail: FlightDetail) {
    }
}
