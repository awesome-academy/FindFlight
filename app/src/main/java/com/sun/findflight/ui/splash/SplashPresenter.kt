package com.sun.findflight.ui.splash

class SplashPresenter : SplashContract.Presenter {

    override fun updateToken() {

    }

    override fun getData() {
        updateToken()
    }
}
