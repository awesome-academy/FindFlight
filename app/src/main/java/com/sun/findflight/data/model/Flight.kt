package com.sun.findflight.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flight(
    val id: Int,
    val originLocationCode: String,
    val destinationLocationCode: String,
    val originCountryImage: String,
    val destinationCountryImage: String,
    val departureDate: String,
    val returnDate: String?,
    val lastTicketingDate: String,
    val numberOfBookableSeats: Int,
    val adults: Int = 1,
    val children: Int = 0,
    val infants: Int = 0,
    val currencyCode: String?,
    val price: Int,
) : Parcelable
