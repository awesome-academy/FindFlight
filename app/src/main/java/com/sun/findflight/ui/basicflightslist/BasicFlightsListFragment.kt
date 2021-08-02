package com.sun.findflight.ui.basicflightslist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.findflight.base.BaseFragment
import com.sun.findflight.data.model.Flight
import com.sun.findflight.databinding.FragmentBasicFlightsListBinding
import com.sun.findflight.ui.basicflightslist.adapter.BasicFlightListAdapter
import com.sun.findflight.ui.main.MainActivity
import com.sun.findflight.utils.closeKeyboard
import com.sun.findflight.utils.hide
import com.sun.findflight.utils.show
import com.sun.findflight.utils.showToast

class BasicFlightsListFragment :
    BaseFragment<FragmentBasicFlightsListBinding>(),
    BasicFlightsListContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBasicFlightsListBinding =
        FragmentBasicFlightsListBinding::inflate
    private val containerContext by lazy { context as MainActivity }
    private var presenter: BasicFlightsListPresenter? = null
    private val flightListAdapter by lazy {
        BasicFlightListAdapter(
            mutableListOf(),
            this::itemFlightClick
        )
    }

    override fun initComponents() {
        viewBinding.recyclerFlights.adapter = flightListAdapter
        containerContext.setBackButtonStatus(true)
        hideLoading()
    }

    override fun initEvents() {
    }

    override fun initData() {

    }

    override fun showFlights(flights: List<Flight>) {
        flightListAdapter.updateData(flights.toMutableList())
    }

    override fun showMessage(messageRes: Int) {
        context?.showToast(resources.getString(messageRes))
    }

    override fun showLoading() {
        flightListAdapter.updateData(mutableListOf())
        viewBinding.progressBarFlight.show()
        closeKeyboard()
    }

    override fun hideLoading() {
        viewBinding.progressBarFlight.hide()
    }

    private fun itemFlightClick(flight: Flight) {

    }

    override fun onDetach() {
        containerContext.setBackButtonStatus(false)
        super.onDetach()
    }
}
