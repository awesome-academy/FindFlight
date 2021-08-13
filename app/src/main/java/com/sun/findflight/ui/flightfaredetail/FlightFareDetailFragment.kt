package com.sun.findflight.ui.flightfaredetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.findflight.R
import com.sun.findflight.base.BaseFragment
import com.sun.findflight.data.model.BasicFlight
import com.sun.findflight.data.model.FlightDetail
import com.sun.findflight.data.model.SegmentFareDetail
import com.sun.findflight.data.model.Traveler
import com.sun.findflight.databinding.FragmentFlightFareDetailBinding
import com.sun.findflight.ui.flightfaredetail.adapter.FareAdapter
import com.sun.findflight.ui.offerflightslist.OfferFlightsListFragment
import kotlin.math.roundToInt

class FlightFareDetailFragment : BaseFragment<FragmentFlightFareDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFlightFareDetailBinding =
        FragmentFlightFareDetailBinding::inflate
    private val fareAdapter by lazy {
        FareAdapter(
            mutableListOf(),
            this::itemFareClick
        )
    }

    override fun initComponents() {
        viewBinding.recyclerFare.adapter = fareAdapter
    }

    override fun initEvents() {
    }

    override fun initData() {
        val bundle = this.arguments
        val flightDetail =
            bundle?.getParcelable<FlightDetail>(OfferFlightsListFragment.DATA_FLIGHT_DETAIL)
        flightDetail?.let { fareAdapter.updateData(it.travelers.first().segmentFareDetail.toMutableList()) }
        calculateAndShowPrice(flightDetail)
    }

    private fun calculateAndShowPrice(flightDetail: FlightDetail?) {
        var calculatePrice = INIT_DOUBLE
        flightDetail?.travelers?.forEach {
            showDetailSegment(it)
            it.price.toDoubleOrNull()?.let { price -> calculatePrice += price }
        }
        flightDetail?.basicFlight?.let { showDetailPrice(it, calculatePrice) }
    }

    private fun showDetailSegment(traveler: Traveler) = with(viewBinding) {
        traveler.run {
            when {
                travelerType.equals(getString(R.string.text_adult), true) -> {
                    textAdultOption.text = fareOption
                    textAdultPrice.text = price
                }
                travelerType.equals(getString(R.string.text_child), true) -> {
                    textChildOption.text = fareOption
                    textChildPrice.text = price
                }
                travelerType.equals(getString(R.string.text_held_infant), true) ->
                    textInfantPrice.text = price
            }
        }
    }

    private fun showDetailPrice(basicFlight: BasicFlight, calculatedPrice: Double) =
        with(viewBinding) {
            var quantity = "$QUANTITY_SYMBOL${basicFlight.adult}"
            textAdultQuantity.text = quantity
            quantity = "$QUANTITY_SYMBOL${basicFlight.child}"
            textChildQuantity.text = quantity
            quantity = "$QUANTITY_SYMBOL${basicFlight.infant}"
            textInfantQuantity.text = quantity

            val totalPrice = "${calculatedPrice.roundToInt()} ${basicFlight.currencyCode}"
            textTotalPrice.text = totalPrice
        }

    private fun itemFareClick(segmentFareDetail: SegmentFareDetail) = Unit

    companion object {
        const val INIT_DOUBLE = 0.0
        const val QUANTITY_SYMBOL = "X"
    }
}
