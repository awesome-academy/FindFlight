package com.sun.findflight.data.model

import android.os.Parcelable
import com.sun.findflight.utils.BaseConst
import kotlinx.parcelize.Parcelize
import org.json.JSONException
import org.json.JSONObject

@Parcelize
data class AnalyticsScore(
    val flightScore: Int?,
    val travelerScore: Int,
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        flightScore = try {
            jsonObject.getJSONObject(BaseConst.FLIGHTS).getInt(BaseConst.SCORE)
        } catch (e: JSONException) {
            null
        },
        travelerScore = jsonObject.getJSONObject(BaseConst.TRAVELERS).getInt(BaseConst.SCORE),
    )
}
