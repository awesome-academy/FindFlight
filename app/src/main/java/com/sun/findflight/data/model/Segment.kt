package com.sun.findflight.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Segment(
    val duration: String,
    val departureCode: String,
    val departureTerminal: Int,
    val departureTime: String,
    val arrivalCode: String,
    val arrivalTerminal: Int,
    val arrivalTime: String,
    val carrierCode: String,
    val carrierNumber: Int,
    val aircraftNumber: Int,
    val numberOfStops: Int,
    val countryCode: String,
    val cabin: String,
    val fareBasis: String,
    val fareClass: String,
    val weightCheckedBad: Double,
    val flightId: Int,
) : Parcelable
