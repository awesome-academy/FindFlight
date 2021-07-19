package com.sun.findflight.data.model

import android.os.Parcelable
import com.sun.findflight.utils.SegmentFareDetailModel
import kotlinx.parcelize.Parcelize
import org.json.JSONException
import org.json.JSONObject

@Parcelize
data class SegmentFareDetail(
    val cabin: String,
    val fareBasis: String,
    val fareClass: String,
    val weightCheckedBad: String?,
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        cabin = jsonObject.getString(SegmentFareDetailModel.CABIN),
        fareBasis = jsonObject.getString(SegmentFareDetailModel.FARE_BASIS),
        fareClass = jsonObject.getString(SegmentFareDetailModel.FARE_CLASS),
        weightCheckedBad = try {
            jsonObject.getJSONObject(SegmentFareDetailModel.CHECKED_BAG)
                .getString(SegmentFareDetailModel.WEIGHT)
        } catch (e: JSONException) {
            null
        },
    )
}
