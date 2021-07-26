package com.sun.findflight.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.sun.findflight.R
import com.sun.findflight.base.BaseFragment
import com.sun.findflight.databinding.FragmentHomeBinding
import com.sun.findflight.utils.chooseDate

class HomeFragment : BaseFragment<FragmentHomeBinding>(), View.OnClickListener {

    private var adult = DEFAULT_ADULT_SEAT
    private var child = NO_PASSENGER
    private var infant = NO_PASSENGER
    private var placeFromCode = ""
    private var placeToCode = ""

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun initComponents() {

    }

    override fun initEvents() {
        viewBinding.run {
            listOf(
                textSearchPlaceFrom, textSearchPlaceTo, textSearchDateFrom, textSearchDateTo,
                textSearchReturnDate, textChangePassenger, imageSwapPlaces, imageCancelPlaceTo,
                imageCancelDateFrom, imageCancelDateTo, imageCancelReturnDate,
                imageDropdownTravelClass, imageDropdownCurrency, buttonFindFlight
            ).forEach { it.setOnClickListener(this@HomeFragment) }
        }

        setPlaceListener(KEY_PLACE_FROM, viewBinding.textSearchPlaceFrom)
        setPlaceListener(KEY_PLACE_TO, viewBinding.textSearchPlaceTo)
        setPassengerListener(viewBinding.textChangePassenger)
    }

    override fun initData() {
        val currency = resources.getStringArray(R.array.currency_list)
        viewBinding.spinnerCurrency.adapter =
            context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, currency) }

        val travelClass = resources.getStringArray(R.array.travel_class)
        viewBinding.spinnerTravelClass.adapter =
            context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, travelClass) }
    }

    override fun onClick(v: View?): Unit = with(viewBinding) {
        when (v) {
            textSearchDateFrom -> parentFragmentManager.chooseDate(
                textSearchDateFrom,
                resources.getString(R.string.text_choose_date)
            )
            textSearchDateTo -> parentFragmentManager.chooseDate(
                textSearchDateTo,
                resources.getString(R.string.text_choose_date)
            )
            textSearchReturnDate -> parentFragmentManager.chooseDate(
                textSearchReturnDate,
                resources.getString(R.string.text_choose_return_date)
            )
            imageSwapPlaces -> swapTextViewContent(textSearchPlaceFrom, textSearchPlaceTo)
            imageCancelPlaceTo -> textSearchPlaceTo.text = ""
            imageCancelDateFrom -> textSearchDateFrom.text = ""
            imageCancelDateTo -> textSearchDateTo.text = ""
            imageCancelReturnDate -> textSearchReturnDate.text = ""
            imageDropdownTravelClass -> spinnerTravelClass.performClick()
            imageDropdownCurrency -> spinnerCurrency.performClick()
            else -> Unit
        }
    }

    private fun setPlaceListener(requestKey: String, textView: TextView) {
        parentFragmentManager.setFragmentResultListener(
            requestKey,
            viewLifecycleOwner
        ) { key, bundle ->
            if (key == requestKey) {
                val result = bundle.get(KEY_DATA) as Pair<*, *>
                if (requestKey == KEY_PLACE_FROM) {
                    placeFromCode = result.first.toString()
                    textView.text = result.second.toString()
                } else if (requestKey == KEY_PLACE_TO) {
                    placeToCode = result.first.toString()
                    textView.text = result.second.toString()
                }
            }
        }
    }

    private fun setPassengerListener(textView: TextView) {
        parentFragmentManager.setFragmentResultListener(
            KEY_ADD_PASSENGER,
            viewLifecycleOwner
        ) { key, bundle ->
            if (key == KEY_ADD_PASSENGER) {
                val result = bundle.get(KEY_DATA) as List<*>
                adult = result[0].toString()
                child = result[1].toString()
                infant = result[2].toString()
                var string = "$adult ${resources.getString(R.string.text_adult)}"
                if (child != NO_PASSENGER) string += ", $child ${resources.getString(R.string.text_child)}"
                if (infant != NO_PASSENGER) string += ", $infant ${resources.getString(R.string.text_infant)}"
                textView.text = string
            }
        }
    }

    private fun swapTextViewContent(textView1: TextView, textView2: TextView) {
        if (textView1.text.isNotEmpty() && textView2.text.isNotEmpty()) {
            val temp = textView1.text
            textView1.text = textView2.text
            textView2.text = temp
        }
    }

    companion object {
        const val KEY_PLACE_FROM = "from_place"
        const val KEY_PLACE_TO = "to_place"
        const val KEY_ADD_PASSENGER = "add_passenger"
        const val KEY_DATA = "data"
        const val NO_PASSENGER = "0"
        const val DEFAULT_ADULT_SEAT = "1"
    }
}
