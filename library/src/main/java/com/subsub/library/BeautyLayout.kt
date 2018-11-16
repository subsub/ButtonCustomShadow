package com.subsub.library

import android.content.Context
import android.util.AttributeSet

/**
 * Created by subkhansarif on 14/11/18
 **/

class BeautyLayout: BeautyView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttrs: Int) : super(context, attributeSet, defStyleAttrs)

    override fun sizeWillChange(w: Int, h: Int, oldw: Int, oldh: Int) {

    }

    override fun sizeDidChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

    }

    override fun onGetAttributes(attr: AttributeSet) {

    }
}