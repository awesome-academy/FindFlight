package com.sun.findflight.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    val type: String,
    val subType: String,
    val name: String,
    val detailedName: String,
    val iataCode: String,
    val cityName: String,
    val countryName: String,
    val countryCode: String,
    val flightScore: Int,
    val travelerScore: Int,
    val image: String,
) : Parcelable
