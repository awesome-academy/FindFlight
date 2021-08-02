package com.sun.findflight.data.repository

import com.sun.findflight.data.model.BasicFlight
import com.sun.findflight.data.model.Flight
import com.sun.findflight.data.source.FlightDataSource
import com.sun.findflight.data.source.ultils.OnDataCallBack

class FlightRepository private constructor(
    private val remote: FlightDataSource.Remote
) : FlightDataSource.Remote {

    override fun getFlights(
        basicFlight: BasicFlight,
        callBack: OnDataCallBack<List<Flight>>
    ) {
        remote.getFlights(basicFlight, callBack)
    }

    override fun getFlightsName(basicFlight: BasicFlight, callBack: OnDataCallBack<String>) {
        remote.getFlightsName(basicFlight, callBack)
    }

    companion object {
        private var instance: FlightRepository? = null

        fun getInstance(remote: FlightDataSource.Remote) =
            instance ?: FlightRepository(remote).also { instance = it }
    }
}
