package com.sun.findflight.utils

import com.sun.findflight.data.repository.PlaceRepository
import com.sun.findflight.data.repository.TokenRepository
import com.sun.findflight.data.source.remote.PlaceRemoteDataSource
import com.sun.findflight.data.source.remote.TokenRemoteDataSource

object RepositoryUtils {

    fun getTokenRepository(): TokenRepository {
        val remote = TokenRemoteDataSource.getInstance()
        return TokenRepository.getInstance(remote)
    }

    fun getPlaceRepository(): PlaceRepository {
        val remote = PlaceRemoteDataSource.getInstance()
        return PlaceRepository.getInstance(remote)
    }
}
