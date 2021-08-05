package com.sun.findflight.ui.offerflightslist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.findflight.base.BaseFragment
import com.sun.findflight.data.model.BasicFlight
import com.sun.findflight.data.model.FlightDetail
import com.sun.findflight.databinding.FragmentOfferFlightsListBinding
import com.sun.findflight.ui.home.HomeFragment
import com.sun.findflight.ui.main.MainActivity
import com.sun.findflight.ui.offerflightslist.adapter.OfferFlightsListAdapter
import com.sun.findflight.utils.RepositoryUtils
import com.sun.findflight.utils.hide
import com.sun.findflight.utils.show
import com.sun.findflight.utils.showToast

class OfferFlightsListFragment :
    BaseFragment<FragmentOfferFlightsListBinding>(),
    OfferFlightsListContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOfferFlightsListBinding =
        FragmentOfferFlightsListBinding::inflate
    private val containerContext by lazy { context as MainActivity }
    private var presenter: OfferFlightsListPresenter? = null
    private val flightDetailListAdapter by lazy {
        OfferFlightsListAdapter(
            mutableListOf(),
            this::itemFlightDetailClick
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerContext.setBottomNavigationVisibility(false)
    }

    override fun initComponents() {
        viewBinding.recyclerFlightsOffer.adapter = flightDetailListAdapter
        containerContext.setBackButtonStatus(true)
        hideLoading()
    }

    override fun initEvents() {
    }

    override fun initData() {
        val repository = RepositoryUtils.getFlightOfferRepository()
        presenter = OfferFlightsListPresenter(this, repository)

        val bundle = this.arguments
        val basicFlight = bundle?.getParcelable<BasicFlight>(HomeFragment.DATA_BASIC_FLIGHT)
        basicFlight?.let { presenter?.getOfferFlights(it) }
    }

    override fun showOfferFlights(flights: List<FlightDetail>) {
        flightDetailListAdapter.updateData(flights.toMutableList())
    }

    override fun showMessage(messageRes: Int) {
        context?.showToast(resources.getString(messageRes))
    }

    override fun showLoading() {
        flightDetailListAdapter.updateData(mutableListOf())
        viewBinding.progressBarFlightOffer.show()
    }

    override fun hideLoading() {
        viewBinding.progressBarFlightOffer.hide()
    }

    private fun itemFlightDetailClick(flightDetail: FlightDetail) {
    }

    override fun onDetach() {
        containerContext.setBottomNavigationVisibility(true)
        super.onDetach()
    }
}
