package com.sun.findflight.data.source.ultils

import android.os.AsyncTask

class DownloaderTask<I, O>(
    private val callback: OnDataCallBack<O>,
    private val handler: (I) -> O?
) : AsyncTask<I, Unit, O?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: I) =
        try {
            handler(params[0])
        } catch (e: Exception) {
            exception = e
            null
        }

    override fun onPostExecute(result: O?) {
        super.onPostExecute(result)
        result?.let {
            callback.onSuccess(it)
        } ?: callback.onFailure(exception)
    }
}
