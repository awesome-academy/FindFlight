package com.sun.findflight.utils

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.datepicker.MaterialDatePicker

fun FragmentManager.addFragment(layout: Int, fragment: Fragment) {
    beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .add(layout, fragment)
        .addToBackStack(null)
        .commit()
}

fun FragmentManager.removeFragment(layout: Int, fragment: Fragment) {
    popBackStack()
    beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        .remove(fragment)
        .commit()
}

fun FragmentManager.replaceFragment(layout: Int, fragment: Fragment) {
    beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .replace(layout, fragment)
        .addToBackStack(null)
        .commit()
}

fun FragmentManager.clearBackStack() = apply {
    popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}

fun FragmentManager.chooseDate(textView: TextView, title: String) {
    val datePicker = MaterialDatePicker
        .Builder
        .datePicker()
        .setTitleText(title)
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        .build()
    datePicker.addOnPositiveButtonClickListener {
        val result = datePicker.selection
        result?.let { textView.text = TimeUtils.convertToDate(it) }
    }
    datePicker.show(this, null)
}
