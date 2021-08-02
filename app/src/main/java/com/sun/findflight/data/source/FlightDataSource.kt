package com.sun.findflight.data.source

import com.sun.findflight.data.model.BasicFlight
import com.sun.findflight.data.model.Flight
import com.sun.findflight.data.model.Place
import com.sun.findflight.data.source.ultils.OnDataCallBack

interface FlightDataSource {
    interface Remote {
        fun getFlights(basicFlight: BasicFlight, callBack: OnDataCallBack<List<Flight>>)
        fun getFlightsName(basicFlight: BasicFlight, callBack: OnDataCallBack<String>)
    }
}
