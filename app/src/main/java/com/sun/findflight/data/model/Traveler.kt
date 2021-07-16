package com.sun.findflight.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Traveler(
    val fareOption: String,
    val travelerType: String,
    val price: Double,
) : Parcelable
