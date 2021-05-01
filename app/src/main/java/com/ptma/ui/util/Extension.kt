package com.ptma.ui.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.zsmb.rainbowcake.dagger.RainbowCakeApplication
import com.google.android.material.snackbar.Snackbar

internal inline fun <reified V : ViewModel> AppCompatActivity.getViewModel(): V {
    val viewModelFactory = (applicationContext as? RainbowCakeApplication)
        ?.injector
        ?.viewModelFactory()
        ?: throw IllegalStateException("The Dagger based getViewModelFromFactory function requires an Application that inherits from RainbowCakeApplication")

    return ViewModelProvider(this, viewModelFactory).get(V::class.java)
}

internal fun Fragment.showSnackbar(id: Int) {
    Snackbar.make(requireView(), getString(id), Snackbar.LENGTH_LONG).show()
}

internal fun String?.safeContainsIgnoreCase(text: String?): Boolean {
    return this?.contains(text ?: "", ignoreCase = true) ?: false
}