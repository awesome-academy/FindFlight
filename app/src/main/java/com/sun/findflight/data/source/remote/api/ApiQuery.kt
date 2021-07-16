package com.sun.findflight.data.source.remote.api

import android.net.Uri

object ApiQuery {

    fun queryToken() = createBaseUri()
        .appendEncodedPath(ApiConstants.TOKEN)
        .toString()

    fun querySearchNearestAirport(latitude: String, longitude: String) = createBaseUri()
        .appendEncodedPath(ApiConstants.SEARCH_PLACE)
        .appendPath(ApiConstants.NEAREST_AIRPORT)
        .appendQueryParameter(ApiConstants.LATITUDE, latitude)
        .appendQueryParameter(ApiConstants.LONGITUDE, longitude)
        .appendQueryParameter(ApiConstants.RADIUS, ApiConstants.DEFAULT_RADIUS)
        .appendQueryParameter(
            ApiConstants.MAX_ITEM_PER_PAGE,
            ApiConstants.DEFAULT_MAX_ITEM_PER_PAGE
        )
        .toString()

    fun querySearchPlaces(keyword: String, countryCode: String?): String {
        val uri = createBaseUri()
            .appendEncodedPath(ApiConstants.SEARCH_PLACE)
            .appendQueryParameter(ApiConstants.SUBTYPE, ApiConstants.DEFAULT_SUB_TYPE)
            .appendQueryParameter(
                ApiConstants.MAX_ITEM_PER_PAGE,
                ApiConstants.DEFAULT_MAX_ITEM_PER_PAGE
            )
        countryCode?.let { uri.appendQueryParameter(ApiConstants.COUNTRY_CODE, countryCode) }
        return uri.toString()
    }

    fun queryFlightSearch(
        origin: String,
        destination: String?,
        departureDate: String?,
        oneWay: Boolean?,
        duration: String?,
        nonStop: Boolean?,
        maxPrice: Int?
    ): String {
        val uri = createBaseUri().appendPath(ApiConstants.SEARCH_FLIGHT)
        destination?.let {
            uri.appendPath(ApiConstants.FLIGHT_CHEAPEST_DATE)
        } ?: uri.appendPath(ApiConstants.FLIGHT_INSPIRATION)
        uri.appendQueryParameter(ApiConstants.ORIGIN, origin)

        destination?.let { uri.appendQueryParameter(ApiConstants.DESTINATION, it) }
        departureDate?.let { uri.appendQueryParameter(ApiConstants.DEPARTURE_DATE, it) }
        oneWay?.let { uri.appendQueryParameter(ApiConstants.ONEWAY, it.toString()) }
        duration?.let { uri.appendQueryParameter(ApiConstants.DURATION, it) }
        nonStop?.let { uri.appendQueryParameter(ApiConstants.NONSTOP, it.toString()) }
        maxPrice?.let { uri.appendQueryParameter(ApiConstants.MAX_PRICE, it.toString()) }
        return uri.toString()
    }

    fun queryMostTraveledDestination(originCityCode: String, period: String) = createBaseUri()
        .appendPath(ApiConstants.CONTENT)
        .appendEncodedPath(ApiConstants.ANALYTICS)
        .appendQueryParameter(ApiConstants.ORIGIN_CITY_CODE, originCityCode)
        .appendQueryParameter(ApiConstants.PERIOD, period)
        .appendQueryParameter(ApiConstants.MAX_ITEMS, ApiConstants.DEFAULT_MAX_ITEM)
        .appendQueryParameter(
            ApiConstants.MAX_ITEM_PER_PAGE,
            ApiConstants.DEFAULT_MAX_ITEM_PER_PAGE
        )
        .toString()

    private fun createBaseUri() = Uri.Builder()
        .scheme(ApiConstants.SCHEME_HTTPS)
        .authority(ApiConstants.AUTHORITY_API_FLIGHT)
        .appendPath(ApiConstants.CONTENT)
}
