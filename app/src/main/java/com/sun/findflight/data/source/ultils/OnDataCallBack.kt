package com.sun.findflight.data.source.ultils

interface OnDataCallBack<O> {
    fun onSuccess(data: O)
    fun onFailure(e: Exception?)
}
