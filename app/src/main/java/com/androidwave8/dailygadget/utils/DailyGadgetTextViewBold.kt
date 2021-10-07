package com.androidwave8.dailygadget.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class DailyGadgetTextViewBold(context: Context, attrs: AttributeSet): AppCompatTextView(context,attrs) {
    init {
        applyFont()
    }

    private fun applyFont() {
        val typeface: Typeface =
            Typeface.createFromAsset(context.assets,"Manrope-Bold.ttf")
        setTypeface(typeface)
    }
}