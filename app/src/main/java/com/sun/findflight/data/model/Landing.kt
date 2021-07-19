package com.sun.findflight.data.model

import android.os.Parcelable
import com.sun.findflight.utils.BaseConst
import com.sun.findflight.utils.SegmentModelConst
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

@Parcelize
data class Landing(
    val departureCode: String,
    val departureTerminal: String,
    val departureTime: String,
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        departureCode = jsonObject.getString(BaseConst.IATA_CODE),
        departureTerminal = jsonObject.getString(SegmentModelConst.TERMINAl),
        departureTime = jsonObject.getString(SegmentModelConst.AT_TIME),
    )
}
