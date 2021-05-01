package com.ptma.ui.util

import android.text.TextWatcher

fun interface TextChangedWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        // Only onTextChanged can be overridden
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        // Only onTextChanged can be overridden
    }
}