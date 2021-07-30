package com.sun.findflight.data.source.remote

import com.sun.findflight.data.model.Place
import com.sun.findflight.data.source.PlaceDataSource
import com.sun.findflight.data.source.remote.api.ApiQuery
import com.sun.findflight.data.source.remote.ultils.getNetworkData
import com.sun.findflight.data.source.ultils.DownloaderTask
import com.sun.findflight.data.source.ultils.OnDataCallBack
import com.sun.findflight.utils.BaseConst
import com.sun.findflight.utils.parseJsonArrayToObjects
import org.json.JSONObject

class PlaceRemoteDataSource private constructor() : PlaceDataSource.Remote {

    override fun searchPlace(keyword: String, callBack: OnDataCallBack<List<Place>>) {
        DownloaderTask<Unit, List<Place>>(callBack) {
            searchPlace(keyword)
        }.execute(Unit)
    }

    override fun searchNearestAirport(
        latitude: String,
        longitude: String,
        callBack: OnDataCallBack<List<Place>>
    ) {
        DownloaderTask<Unit, List<Place>>(callBack) {
            searchNearestAirport(latitude, longitude)
        }.execute(Unit)
    }

    private fun searchPlace(keyword: String): List<Place> =
        JSONObject(getNetworkData(ApiQuery.querySearchPlaces(keyword)))
            .getString(BaseConst.DATA)
            .parseJsonArrayToObjects()

    private fun searchNearestAirport(latitude: String, longitude: String): List<Place> =
        JSONObject(getNetworkData(ApiQuery.querySearchNearestAirport(latitude, longitude)))
            .getString(BaseConst.DATA)
            .parseJsonArrayToObjects()

    companion object {
        private var instance: PlaceRemoteDataSource? = null

        fun getInstance() = instance ?: PlaceRemoteDataSource().also { instance = it }
    }
}
