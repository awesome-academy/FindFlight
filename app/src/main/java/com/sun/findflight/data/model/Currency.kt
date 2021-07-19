package com.sun.findflight.data.model

import android.os.Parcelable
import com.sun.findflight.utils.BaseConst
import com.sun.findflight.utils.FlightModelConst
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

@Parcelize
data class Currency(
    val currencyCode: String,
    val price: String,
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        currencyCode = jsonObject.getString(FlightModelConst.CURRENCY),
        price = jsonObject.getString(BaseConst.TOTAL),
    )
}
