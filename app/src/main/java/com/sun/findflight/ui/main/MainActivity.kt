package com.sun.findflight.ui.main

import android.content.Context
import android.content.Intent
import com.sun.findflight.base.BaseActivity
import com.sun.findflight.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val viewBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun initComponents() {

    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
