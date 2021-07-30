package com.sun.findflight.ui.searchPlace

import com.sun.findflight.R
import com.sun.findflight.data.model.Place
import com.sun.findflight.data.repository.PlaceRepository
import com.sun.findflight.data.source.ultils.OnDataCallBack

class SearchPlacePresenter(
    private val view: SearchPlaceContract.View,
    private val repository: PlaceRepository
) : SearchPlaceContract.Presenter {

    override fun getPlaces(keyword: String) {
        view.showLoading()
        repository.searchPlace(keyword, object : OnDataCallBack<List<Place>> {
            override fun onSuccess(data: List<Place>) = dataAvailable(data)

            override fun onFailure(e: Exception?) = dataUnavailable()
        })
    }

    override fun getNearestPlaces(latitude: String, longitude: String) {
        view.showLoading()
        repository.searchNearestAirport(latitude, longitude, object : OnDataCallBack<List<Place>> {
            override fun onSuccess(data: List<Place>) = dataAvailable(data)

            override fun onFailure(e: Exception?) = dataUnavailable()
        })
    }

    private fun dataAvailable(data: List<Place>) {
        if (data.isEmpty()) view.showMessage(R.string.error_cant_find_place)
        view.hideLoading()
        view.showPlaces(data)
    }

    private fun dataUnavailable() {
        view.showMessage(R.string.error_cant_find_place)
        view.hideLoading()
    }

    override fun getData() {

    }
}
