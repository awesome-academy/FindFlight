package com.sun.findflight.ui.splash

import com.sun.findflight.base.BasePresenter

interface SplashContract {
    interface View {
        fun notifyTokenUpdated()
    }

    interface Presenter : BasePresenter {
        fun updateToken()
    }
}
