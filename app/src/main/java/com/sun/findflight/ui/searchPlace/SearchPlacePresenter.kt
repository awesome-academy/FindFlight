package com.sun.findflight.ui.searchPlace

import com.sun.findflight.R
import com.sun.findflight.data.model.Place

class SearchPlacePresenter(
    private val view: SearchPlaceContract.View,
) : SearchPlaceContract.Presenter {

    override fun getPlaces(keyword: String) {
        view.showLoading()
    }

    override fun getNearestPlaces(latitude: String, longitude: String) {
        view.showLoading()
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
