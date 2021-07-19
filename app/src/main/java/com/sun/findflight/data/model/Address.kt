package com.sun.findflight.data.model

import android.os.Parcelable
import com.sun.findflight.utils.PlaceModelConst
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

@Parcelize
data class Address(
    val cityName: String,
    val countryName: String,
    val countryCode: String,
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        cityName = jsonObject.getString(PlaceModelConst.CITY_NAME),
        countryName = jsonObject.getString(PlaceModelConst.COUNTRY_NAME),
        countryCode = jsonObject.getString(PlaceModelConst.COUNTRY_CODE),
    )
}
