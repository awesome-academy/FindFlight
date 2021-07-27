package com.sun.findflight.ui.addpassenger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sun.findflight.R
import com.sun.findflight.databinding.FragmentAddPassengerBinding
import com.sun.findflight.ui.home.HomeFragment
import com.sun.findflight.utils.showToast

class AddPassengerFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private var _viewBinding: FragmentAddPassengerBinding? = null
    private lateinit var viewBinding: FragmentAddPassengerBinding
    private var total = DEFAULT_TOTAL

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentAddPassengerBinding.inflate(inflater, container, false)
        _viewBinding?.let { viewBinding = it }
        return _viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvents()
    }

    override fun onClick(v: View?) = with(viewBinding) {
        when (v) {
            imageAddAdult -> addPassenger(textPassengerNumberAdult)
            imageMinusAdult -> minusPassenger(textPassengerNumberAdult)
            imageAddChild -> addPassenger(textPassengerNumberChild)
            imageMinusChild -> minusPassenger(textPassengerNumberChild)
            imageAddInfant -> addPassenger(textPassengerNumberInfant)
            imageMinusInfant -> minusPassenger(textPassengerNumberInfant)
            buttonApplyPassenger -> setResult()
        }
    }

    private fun initEvents() {
        viewBinding.run {
            listOf(
                imageMinusAdult,
                imageAddAdult,
                imageAddChild,
                imageMinusChild,
                imageAddInfant,
                imageMinusInfant,
                buttonApplyPassenger
            ).forEach {
                it.setOnClickListener(this@AddPassengerFragment)
            }
        }
    }

    private fun addPassenger(textView: TextView) {
        try {
            if (total < MAX_PEOPLE) {
                var value = textView.text.toString().toInt()
                ++value
                textView.text = value.toString()
                ++total
            } else {
                context?.showToast(
                    resources.getString(R.string.error_over_people_number),
                    Toast.LENGTH_SHORT
                )
            }
        } catch (e: NumberFormatException) {
            context?.showToast(resources.getString(R.string.error_common), Toast.LENGTH_SHORT)
        }
    }

    private fun minusPassenger(textView: TextView) {
        try {
            var value = textView.text.toString().toInt()
            if (value > MIN_PEOPLE) {
                --value
                if (textView == viewBinding.textPassengerNumberAdult && value == MIN_PEOPLE) {
                    context?.showToast(
                        resources.getString(R.string.error_below_adult_number),
                        Toast.LENGTH_SHORT
                    )
                } else {
                    textView.text = value.toString()
                    --total
                }
            }
        } catch (e: NumberFormatException) {
            context?.showToast(resources.getString(R.string.error_common), Toast.LENGTH_SHORT)
        }
    }

    private fun setResult() {
        var bundle: Bundle
        try {
            with(viewBinding) {
                bundle = bundleOf(
                    HomeFragment.KEY_DATA to listOf(
                        textPassengerNumberAdult.text.toString().toInt(),
                        textPassengerNumberChild.text.toString().toInt(),
                        textPassengerNumberInfant.text.toString().toInt(),
                    )
                )
            }
            parentFragmentManager.setFragmentResult(
                HomeFragment.KEY_ADD_PASSENGER,
                bundle
            )
            this@AddPassengerFragment.dismiss()
        } catch (e: NumberFormatException) {
            context?.showToast(resources.getString(R.string.error_common), Toast.LENGTH_SHORT)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }

    companion object {
        const val MAX_PEOPLE = 9
        const val MIN_PEOPLE = 0
        const val DEFAULT_TOTAL = 1
    }
}
