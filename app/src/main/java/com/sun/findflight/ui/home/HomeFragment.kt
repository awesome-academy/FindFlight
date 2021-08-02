package com.sun.findflight.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.sun.findflight.R
import com.sun.findflight.base.BaseFragment
import com.sun.findflight.data.model.BasicFlight
import com.sun.findflight.data.model.Place
import com.sun.findflight.databinding.FragmentHomeBinding
import com.sun.findflight.ui.addpassenger.AddPassengerFragment
import com.sun.findflight.ui.basicflightslist.BasicFlightsListFragment
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
            textSearchDateTo -> {
                if (textSearchDateFrom.text.isEmpty()) {
                    context?.showToast(getString(R.string.text_choose_date_from_first))
                } else {
                    parentFragmentManager.chooseDate(
                        textSearchDateTo,
                        resources.getString(R.string.text_choose_date)
                    )
                }
            }
            textSearchReturnDate -> parentFragmentManager.chooseDate(
                textSearchReturnDate,
                resources.getString(R.string.text_choose_return_date)
            )
            textChangePassenger -> AddPassengerFragment().show(parentFragmentManager, null)
            imageSwapPlaces -> swapTextViewContent(textSearchPlaceFrom, textSearchPlaceTo)
            imageCancelPlaceTo -> {
                placeToObject = null
                textSearchPlaceTo.text = ""
            }
            imageCancelDateFrom -> {
                textSearchDateFrom.text = ""
                textSearchDateTo.text = ""
            }
            imageCancelDateTo -> textSearchDateTo.text = ""
            imageCancelReturnDate -> textSearchReturnDate.text = ""
            imageDropdownTravelClass -> spinnerTravelClass.performClick()
            imageDropdownCurrency -> spinnerCurrency.performClick()
            buttonFindFlight -> findFlight()
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

    private fun findFlight() {
        if (placeFromIsValid()) {
            with(viewBinding) {
                var basicFlight: BasicFlight? = null
                val oneWay = switchOneway.isChecked
                val dateFrom = extractString(textSearchDateFrom)
                val dateTo = extractString(textSearchDateTo)
                val returnDate = extractString(textSearchReturnDate)
                val travelClass = spinnerTravelClass.selectedItem.toString()
                val currencyCode = spinnerCurrency.selectedItem.toString().split(" ")[0]
                val fragment = BasicFlightsListFragment()
                var departureDate: String? = null
                dateFrom?.let { departureDate = it }
                dateTo?.let { departureDate += ",$it" }
                placeFromObject?.let {
                    basicFlight = BasicFlight(
                        origin = it.iataCode,
                        destination = placeToObject?.iataCode,
                        departureDate = departureDate,
                        oneWay = oneWay,
                        returnDate = returnDate,
                        adult = adult,
                        child = child,
                        infant = infant,
                        travelClass = travelClass,
                        currencyCode = currencyCode
                    )
                }
                openFlightFragment(basicFlight, fragment)
            }
        }
    }

    private fun placeFromIsValid() = viewBinding.textSearchPlaceFrom.text.isNotEmpty()

    private fun extractString(textView: TextView) =
        if (textView.text.isNotEmpty()) textView.text.toString() else null

    private fun openFlightFragment(basicFlight: BasicFlight?, fragment: Fragment) {
        fragment.arguments = bundleOf(DATA_BASIC_FLIGHT to basicFlight)
        parentFragmentManager.addFragment(R.id.frameMain, fragment)
    }

    companion object {
        const val KEY_PLACE_FROM = "from_place"
        const val KEY_PLACE_TO = "to_place"
        const val KEY_ADD_PASSENGER = "add_passenger"
        const val KEY_DATA = "data"
        const val NO_PASSENGER = "0"
        const val DEFAULT_ADULT_SEAT = "1"
        const val DATA_BASIC_FLIGHT = "basic_flight_data"
    }
}
