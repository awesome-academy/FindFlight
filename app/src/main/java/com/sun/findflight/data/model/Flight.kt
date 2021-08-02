package com.sun.findflight.data.model

import android.os.Parcelable
import com.sun.findflight.utils.BaseConst
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

@Parcelize
data class Flight(
    val originCode: String,
    val destinationCode: String,
    var origin: String?,
    var destination: String?,
    val departureDate: String,
    val returnDate: String,
    val flightLink: String,
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        originCode = jsonObject.getString(BaseConst.ORIGIN),
        destinationCode = jsonObject.getString(BaseConst.DESTINATION),
        origin = null,
        destination = null,
        departureDate = jsonObject.getString(BaseConst.DEPARTURE_DATE),
        returnDate = jsonObject.getString(BaseConst.RETURN_DATE),
        flightLink = jsonObject.getJSONObject(BaseConst.LINK).getString(BaseConst.FLIGHT_OFFERS),
    )
}
