package com.sun.findflight.ui.main

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.View
import com.google.android.material.navigation.NavigationBarView
import com.sun.findflight.R
import com.sun.findflight.base.BaseActivity
import com.sun.findflight.databinding.ActivityMainBinding
import com.sun.findflight.ui.home.HomeFragment

class MainActivity : BaseActivity<ActivityMainBinding>(), View.OnClickListener {

    override val viewBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val onBottomNavigation = NavigationBarView.OnItemSelectedListener {
        when (it.itemId) {
            R.id.menuHome -> openFragment(HomeFragment())
        }
        true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun initComponents() {
        with(viewBinding) {
            fabHome.setOnClickListener(this@MainActivity)
            bottomNavigationView.apply {
                setOnItemSelectedListener(onBottomNavigation)
                background = null
                selectedItemId = R.id.menuHome
            }
        }
    }

    override fun onClick(v: View?) = with(viewBinding) {
        when (v) {
            fabHome -> bottomNavigationView.selectedItemId = R.id.menuHome
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
