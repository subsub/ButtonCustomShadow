package com.subsub.buttoncustomshadow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.subsub.buttoncustomshadow.MainActivity
import com.subsub.buttoncustomshadow.R
import kotlinx.android.synthetic.main.fragment_button.*

/**
 * Created by subkhansarif on 14/11/18
 **/

class ButtonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_button, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val activity = activity as MainActivity
        activity.applyCurrent(button.myElevation, button.customShadowRadius, button.xOffset, button.yOffset, button.shadowHeight, button.shadowWidth)
        activity.listener = {
            button.myElevation = activity.viewElevation
            button.customShadowRadius = activity.viewShadowRadius
            button.xOffset = activity.viewXOffset
            button.yOffset = activity.viewYOffset
            button.shadowHeight = activity.viewShadowHeight
            button.shadowWidth = activity.viewShadowWidth
        }
    }
}