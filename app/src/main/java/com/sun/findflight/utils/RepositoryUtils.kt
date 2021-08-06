package com.sun.findflight.utils

import com.sun.findflight.data.repository.*
import com.sun.findflight.data.source.remote.*

object RepositoryUtils {

    fun getTokenRepository(): TokenRepository {
        val remote = TokenRemoteDataSource.getInstance()
        return TokenRepository.getInstance(remote)
    }

    fun getPlaceRepository(): PlaceRepository {
        val remote = PlaceRemoteDataSource.getInstance()
        return PlaceRepository.getInstance(remote)
    }

    fun getFlightRepository(): FlightRepository {
        val remote = FlightRemoteDataSource.getInstance()
        return FlightRepository.getInstance(remote)
    }

    fun getFlightOfferRepository(): FlightOfferRepository {
        val remote = FlightOfferRemoteDataSource.getInstance()
        return FlightOfferRepository.getInstance(remote)
    }

    fun getFlightInfoRepository(): FlightInfoRepository {
        val remote = FlightInfoRemoteDataSource.getInstance()
        return FlightInfoRepository.getInstance(remote)
    }
}
