package com.sun.findflight.ui.offerflightslist

import com.sun.findflight.R
import com.sun.findflight.data.model.BasicFlight
import com.sun.findflight.data.model.FlightDetail
import com.sun.findflight.data.repository.FlightOfferRepository
import com.sun.findflight.data.source.ultils.OnDataCallBack

class OfferFlightsListPresenter(
    private val view: OfferFlightsListContract.View,
    private val repository: FlightOfferRepository
) : OfferFlightsListContract.Presenter {

    override fun getOfferFlights(basicFlight: BasicFlight) {
        view.showLoading()
        repository.getOfferFlights(
            basicFlight,
            object : OnDataCallBack<List<FlightDetail>> {
                override fun onSuccess(data: List<FlightDetail>) {
                    if (data.isEmpty()) {
                        view.showMessage(R.string.error_cant_find_flight)
                    }
                    view.hideLoading()
                    view.showOfferFlights(data)
                }

                override fun onFailure(e: Exception?) {
                    view.showMessage(R.string.error_cant_find_flight)
                    view.hideLoading()
                }
            })
    }

    override fun getData() {
    }
}
