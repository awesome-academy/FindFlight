package com.sun.findflight.ui.basicflightslist

import com.sun.findflight.base.BasePresenter
import com.sun.findflight.base.BaseView
import com.sun.findflight.data.model.Flight

interface BasicFlightsListContract {
    interface View : BaseView {
        fun showFlights(flights: List<Flight>)
    }

    interface Presenter : BasePresenter {

    }
}
