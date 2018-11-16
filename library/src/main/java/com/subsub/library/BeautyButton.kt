package com.subsub.library

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView

/**
 * Created by subkhansarif on 26/09/18
 **/

class BeautyButton : BeautyView {

    var text: String = ""
    private lateinit var textView: TextView

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttrs: Int) : super(context, attributeSet, defStyleAttrs)

    override fun onGetAttributes(attr: AttributeSet) {
        with(context.obtainStyledAttributes(attr, R.styleable.BeautyButton)) {
            text = getString(R.styleable.BeautyButton_text) ?: ""
        }

        textView = TextView(context, attr)
        textView.elevation = 0f
        textView.text = text
        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        layoutParams.marginEnd = 0
        layoutParams.marginStart = 0
        layoutParams.topMargin = 0
        layoutParams.bottomMargin = 0
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.isEnabled = false
        textView.setPadding(0, 0, 0, 0)
        textView.layoutParams = layoutParams
        addView(textView)
    }

    override fun sizeWillChange(w: Int, h: Int, oldw: Int, oldh: Int) {

    }

    override fun sizeDidChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

    }
}