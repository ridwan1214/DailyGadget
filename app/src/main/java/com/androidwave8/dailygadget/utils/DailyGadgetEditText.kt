package com.androidwave8.dailygadget.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class DailyGadgetEditText(context: Context, attrs: AttributeSet): AppCompatEditText(context, attrs) {

    init {
        applyFont()
    }

    private fun applyFont() {
        val typeFace: Typeface =
            Typeface.createFromAsset(context.assets, "Manrope-Bold.ttf")
        setTypeface(typeFace)
    }
}