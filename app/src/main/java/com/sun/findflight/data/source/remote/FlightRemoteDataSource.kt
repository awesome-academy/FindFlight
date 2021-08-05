package com.sun.findflight.data.source.remote

import com.sun.findflight.data.model.BasicFlight
import com.sun.findflight.data.model.Flight
import com.sun.findflight.data.source.FlightDataSource
import com.sun.findflight.data.source.remote.api.ApiQuery
import com.sun.findflight.data.source.remote.ultils.getNetworkData
import com.sun.findflight.data.source.ultils.DownloaderTask
import com.sun.findflight.data.source.ultils.OnDataCallBack
import com.sun.findflight.utils.BaseConst
import com.sun.findflight.utils.parseJsonArrayToObjects
import org.json.JSONObject

class FlightRemoteDataSource private constructor() : FlightDataSource.Remote {

    override fun getFlights(
        basicFlight: BasicFlight,
        callBack: OnDataCallBack<List<Flight>>
    ) {
        DownloaderTask<Unit, List<Flight>>(callBack) {
            getFlights(basicFlight)
        }.execute(Unit)
    }

    override fun getFlightsName(
        basicFlight: BasicFlight,
        callBack: OnDataCallBack<String>
    ) {
        DownloaderTask<Unit, String>(callBack) {
            getFlightsName(basicFlight)
        }.execute(Unit)
    }

    private fun getFlights(basicFlight: BasicFlight) =
        getJSONData(basicFlight)
            .getString(BaseConst.DATA)
            .parseJsonArrayToObjects<Flight>()

    private fun getFlightsName(basicFlight: BasicFlight) =
        getJSONData(basicFlight)
            .getJSONObject(BaseConst.DICTIONARY)
            .getJSONObject(BaseConst.LOCATION)
            .toString()

    private fun getJSONData(basicFlight: BasicFlight) = JSONObject(
        getNetworkData(
            ApiQuery.queryFlightSearch(
                basicFlight.originCode,
                basicFlight.destinationCode,
                basicFlight.departureDate,
                basicFlight.oneWay
            )
        )
    )

    companion object {
        private var instance: FlightRemoteDataSource? = null

        fun getInstance() = instance ?: FlightRemoteDataSource().also { instance = it }
    }
}
