package com.sun.findflight.data.model

import android.os.Parcelable
import com.sun.findflight.utils.BaseConst
import com.sun.findflight.utils.PlaceModelConst
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

@Parcelize
data class Place(
    val type: String,
    val subType: String,
    val name: String,
    val detailedName: String,
    val timeZone: String,
    val iataCode: String,
    val address: Address,
    val analyticsScore: AnalyticsScore?,
    var image: String?,
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        type = jsonObject.getString(PlaceModelConst.PLACE_TYPE),
        subType = jsonObject.getString(PlaceModelConst.PLACE_SUBTYPE),
        name = jsonObject.getString(PlaceModelConst.PLACE_NAME),
        detailedName = jsonObject.getString(PlaceModelConst.PLACE_DETAILED_NAME),
        timeZone = jsonObject.getString(PlaceModelConst.TIME_ZONE),
        iataCode = jsonObject.getString(BaseConst.IATA_CODE),
        address = Address(jsonObject.getJSONObject(PlaceModelConst.ADDRESS)),
        analyticsScore = try {
            AnalyticsScore(jsonObject.getJSONObject(BaseConst.ANALYTICS))
        } catch (e: Exception) {
            null
        },
        image = null
    )
}
