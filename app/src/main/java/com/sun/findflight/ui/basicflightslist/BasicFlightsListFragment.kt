package com.sun.findflight.ui.basicflightslist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.findflight.base.BaseFragment
import com.sun.findflight.data.model.BasicFlight
import com.sun.findflight.data.model.Flight
import com.sun.findflight.databinding.FragmentBasicFlightsListBinding
import com.sun.findflight.ui.basicflightslist.adapter.BasicFlightListAdapter
import com.sun.findflight.ui.home.HomeFragment
import com.sun.findflight.ui.main.MainActivity
import com.sun.findflight.utils.*

class BasicFlightsListFragment :
    BaseFragment<FragmentBasicFlightsListBinding>(),
    BasicFlightsListContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBasicFlightsListBinding =
        FragmentBasicFlightsListBinding::inflate
    private val containerContext by lazy { context as MainActivity }
    private var presenter: BasicFlightsListPresenter? = null
    private var basicFlight: BasicFlight? = null
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
        val repository = RepositoryUtils.getFlightRepository()
        presenter = BasicFlightsListPresenter(this, repository)

        val bundle = this.arguments
        basicFlight = bundle?.getParcelable(HomeFragment.DATA_BASIC_FLIGHT)
        basicFlight?.let { getFlights(it) }
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

    private fun getFlights(basicFlight: BasicFlight) {
        presenter?.getFlights(basicFlight)
    }

    private fun itemFlightClick(flight: Flight) {

    }

    override fun onDetach() {
        containerContext.setBackButtonStatus(false)
        super.onDetach()
    }

}
