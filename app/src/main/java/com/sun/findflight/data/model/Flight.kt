package com.sun.findflight.data.model

import android.os.Parcelable
import com.sun.findflight.utils.BaseConst
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

@Parcelize
data class Flight(
    val origin: String,
    val destination: String,
    val departureDate: String,
    val returnDate: String,
    val flightLink: String,
    var originCountryImage: String?,
    var destinationCountryImage: String?,
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        origin = jsonObject.getString(BaseConst.ORIGIN),
        destination = jsonObject.getString(BaseConst.DESTINATION),
        departureDate = jsonObject.getString(BaseConst.DEPARTURE_DATE),
        returnDate = jsonObject.getString(BaseConst.DEPARTURE_DATE),
        flightLink = jsonObject.getJSONObject(BaseConst.LINK).getString(BaseConst.FLIGHT_OFFERS),
        originCountryImage = null,
        destinationCountryImage = null,
    )
}
