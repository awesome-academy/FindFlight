package com.sun.findflight.ui.offerflightslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.findflight.R
import com.sun.findflight.base.BaseAdapter
import com.sun.findflight.base.BaseViewHolder
import com.sun.findflight.data.model.FlightDetail
import com.sun.findflight.data.source.remote.api.ApiQuery
import com.sun.findflight.databinding.ItemFlightDetailBinding
import com.sun.findflight.utils.loadImage
import java.util.*

class OfferFlightsListAdapter(
    items: MutableList<FlightDetail>,
    private val onItemClick: (FlightDetail) -> Unit
) : BaseAdapter<FlightDetail, OfferFlightsListAdapter.FlightListViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightListViewHolder =
        FlightListViewHolder(
            ItemFlightDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClick
        )

    class FlightListViewHolder(
        private val viewBinding: ItemFlightDetailBinding,
        onItemClick: (FlightDetail) -> Unit,
    ) : BaseViewHolder<FlightDetail>(viewBinding, onItemClick) {

        override fun bindData(itemData: FlightDetail) {
            super.bindData(itemData)
            var carrierList = ""
            with(viewBinding) {
                itemData.run {
                    textFlightOrigin.text = basicFlight?.originCode
                    textFlightDes.text = basicFlight?.destinationCode
                    textFlightDuration.text = duration.removeRange(0, 2)
                    if (segments.size == ONE_SEGMENT) {
                        textFlightStopNum.text = itemView.context.getString(R.string.text_direct)
                    } else {
                        val stop =
                            "${segments.size} ${itemView.context.getString(R.string.text_stops)}"
                        textFlightStopNum.text = stop
                    }
                    textShowDepartureDate.text = basicFlight?.departureDate
                    val price = "${currency.price} (${currency.currencyCode})"
                    textShowPrice.text = price
                    airlinesCode.forEach {
                        carrierList += "$it,"
                    }
                    carrierList = carrierList.removeSuffix(COMMA)
                    textShowCarrier.text = carrierList
                    textShowSeatNum.text = numberOfBookableSeats
                    try {
                        segments.first().departure.countryCode?.let {
                            imageFlightOrigin.loadImage(
                                ApiQuery.queryImage(it.lowercase(Locale.getDefault()))
                            )
                        }
                        segments.last().arrival.countryCode?.let {
                            imageFlightDes.loadImage(
                                ApiQuery.queryImage(it.lowercase(Locale.getDefault()))
                            )
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

        companion object {
            const val ONE_SEGMENT = 1
            const val COMMA = ","
        }
    }
}
