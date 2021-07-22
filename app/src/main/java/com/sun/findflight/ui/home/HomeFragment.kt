package com.sun.findflight.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.findflight.base.BaseFragment
import com.sun.findflight.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeContract.View {

    private var homePresenter: HomeContract.Presenter? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun initComponents() {
    }

    override fun initEvents() {
    }

    override fun initData() {
    }

    override fun showMessage(data: Any) {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }
}
