package com.sun.findflight.ui.basicflightslist

import com.sun.findflight.R
import com.sun.findflight.data.model.BasicFlight
import com.sun.findflight.data.model.Flight
import com.sun.findflight.data.model.Place
import com.sun.findflight.data.repository.FlightRepository
import com.sun.findflight.data.source.ultils.OnDataCallBack
import com.sun.findflight.utils.BaseConst
import org.json.JSONException
import org.json.JSONObject

class BasicFlightsListPresenter(
    private val view: BasicFlightsListContract.View,
    private val repository: FlightRepository
) : BasicFlightsListContract.Presenter {

    override fun getFlights(basicFlight: BasicFlight) {
        view.showLoading()
        repository.getFlights(basicFlight, object : OnDataCallBack<List<Flight>> {
            override fun onSuccess(data: List<Flight>) {
                if (data.isEmpty()) {
                    dataUnavailable()
                } else {
                    getLocation(data, basicFlight)
                }
            }

            override fun onFailure(e: Exception?) = dataUnavailable()
        })
    }

    fun getLocation(dataFlight: List<Flight>, basicFlight: BasicFlight) {
        repository.getFlightsName(basicFlight, object : OnDataCallBack<String> {
            override fun onSuccess(data: String) {
                if (data.isNotEmpty()) {
                    val jsonObject = JSONObject(data)
                    dataFlight.forEach {
                        it.origin = try {
                            jsonObject.getJSONObject(it.originCode)
                                .getString(BaseConst.DETAILED_NAME)
                        } catch (e: JSONException) {
                            it.originCode
                        }
                        it.destination = try {
                            jsonObject.getJSONObject(it.destinationCode)
                                .getString(BaseConst.DETAILED_NAME)
                        } catch (e: JSONException) {
                            it.destinationCode
                        }
                    }
                }
                dataAvailable(dataFlight)
            }

            override fun onFailure(e: Exception?) = dataAvailable(dataFlight)
        })
    }

    private fun dataAvailable(data: List<Flight>) {
        view.hideLoading()
        view.showFlights(data)
    }

    private fun dataUnavailable() {
        view.showMessage(R.string.error_cant_find_place)
        view.hideLoading()
    }

    override fun getData() {

    }
}
