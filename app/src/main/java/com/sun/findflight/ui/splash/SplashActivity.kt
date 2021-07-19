package com.sun.findflight.ui.splash

import android.os.Handler
import android.os.Looper
import com.sun.findflight.base.BaseActivity
import com.sun.findflight.databinding.ActivitySplashBinding
import com.sun.findflight.ui.main.MainActivity

private const val TIME_WAITING = 2000L

class SplashActivity : BaseActivity<ActivitySplashBinding>(), SplashContract.View {

    override val viewBinding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun initComponents() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(MainActivity.getIntent(this))
            finish()
        }, TIME_WAITING)
    }

    override fun notifyTokenUpdated() {

    }
}
