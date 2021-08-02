package com.sun.findflight.data.model

import android.os.Parcelable
import com.sun.findflight.ui.home.HomeFragment
import kotlinx.parcelize.Parcelize

@Parcelize
data class BasicFlight(
    val origin: String,
    val destination: String?,
    val departureDate: String?,
    val oneWay: Boolean = false,
    val returnDate: String? = null,
    val adult: String = HomeFragment.DEFAULT_ADULT_SEAT,
    val child: String = HomeFragment.NO_PASSENGER,
    val infant: String = HomeFragment.NO_PASSENGER,
    val travelClass: String,
    val currencyCode: String
) : Parcelable
