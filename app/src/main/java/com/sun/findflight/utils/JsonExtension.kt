package com.sun.findflight.utils

import com.sun.findflight.R
import com.sun.findflight.data.model.*
import org.json.JSONArray
import org.json.JSONException

inline fun <reified T> String.parseJsonArrayToObjects() = JSONArray(this).run {
    MutableList(length()) { index ->
        when (T::class) {
            Place::class -> Place(getJSONObject(index)) as T
            Flight::class -> Flight(getJSONObject(index)) as T
            FlightDetail::class -> FlightDetail(getJSONObject(index)) as T
            Segment::class -> Segment(getJSONObject(index)) as T
            String::class -> getString(index) as T
            Traveler::class -> Traveler(getJSONObject(index)) as T
            SegmentFareDetail::class -> SegmentFareDetail(getJSONObject(index)) as T
            else -> throw JSONException(R.string.error_parse_json.toString())
        }
    }
}
