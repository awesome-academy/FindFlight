package com.sun.findflight.utils

import com.sun.findflight.data.source.remote.api.ApiQuery.queryToken
import com.sun.findflight.data.source.remote.ultils.getNetworkAuthority

fun requestNewToken() = getNetworkAuthority(queryToken())
