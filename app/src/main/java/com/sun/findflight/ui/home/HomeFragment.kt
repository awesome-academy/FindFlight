package com.sun.findflight.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.sun.findflight.R
import com.sun.findflight.base.BaseFragment
import com.sun.findflight.data.model.Place
import com.sun.findflight.databinding.FragmentHomeBinding
import com.sun.findflight.ui.addpassenger.AddPassengerFragment
import com.sun.findflight.ui.searchPlace.SearchPlaceFragment
import com.sun.findflight.utils.*

class HomeFragment : BaseFragment<FragmentHomeBinding>(), View.OnClickListener {

    private var adult = DEFAULT_ADULT_SEAT
    private var child = NO_PASSENGER
    private var infant = NO_PASSENGER
    private var placeFromObject: Place? = null
    private var placeToObject: Place? = null

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
            textSearchPlaceFrom -> parentFragmentManager.addFragment(
                R.id.frameMain,
                SearchPlaceFragment(),
                KEY_PLACE_FROM
            )
            textSearchPlaceTo -> parentFragmentManager.addFragment(
                R.id.frameMain,
                SearchPlaceFragment(),
                KEY_PLACE_TO
            )
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
            textChangePassenger -> AddPassengerFragment().show(parentFragmentManager, null)
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
                val result = bundle.getParcelable<Place>(KEY_DATA)
                if (requestKey == KEY_PLACE_FROM) {
                    placeFromObject = result
                    textView.text = result?.detailedName
                } else if (requestKey == KEY_PLACE_TO) {
                    placeToObject = result
                    textView.text = result?.detailedName
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
            textView1.text = textView2.text.also { textView2.text = textView1.text }
            placeFromObject = placeToObject.also { placeToObject = placeFromObject }
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
