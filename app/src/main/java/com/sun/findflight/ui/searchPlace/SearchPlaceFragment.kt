package com.sun.findflight.ui.searchPlace

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sun.findflight.base.BaseFragment
import com.sun.findflight.data.model.Place
import com.sun.findflight.databinding.FragmentSearchPlaceBinding
import com.sun.findflight.utils.*

class SearchPlaceFragment : BaseFragment<FragmentSearchPlaceBinding>(), SearchPlaceContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchPlaceBinding =
        FragmentSearchPlaceBinding::inflate

    override fun initComponents() {
        hideLoading()
    }

    override fun initEvents() {

    }

    override fun initData() {

    }

    override fun showPlaces(listPlace: List<Place>) {

    }

    override fun showMessage(data: Any) {
        context?.showToast(data.toString())
    }

    override fun showLoading() {
        viewBinding.progressBarPlace.show()
    }

    override fun hideLoading() {
        viewBinding.progressBarPlace.hide()
    }

    private fun itemPlaceClick(place: Place) {

    }

}
