package com.subsub.buttoncustomshadow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.subsub.buttoncustomshadow.MainActivity
import com.subsub.buttoncustomshadow.R
import kotlinx.android.synthetic.main.fragment_layout.*

/**
 * Created by subkhansarif on 14/11/18
 **/

class LayoutFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = activity as MainActivity
        activity.applyCurrent(l_layout.myElevation, l_layout.customShadowRadius, l_layout.xOffset, l_layout.yOffset, l_layout.shadowHeight, l_layout.shadowWidth)
        activity.listener = {
            l_layout.myElevation = activity.viewElevation
            l_layout.customShadowRadius = activity.viewShadowRadius
            l_layout.xOffset = activity.viewXOffset
            l_layout.yOffset = activity.viewYOffset
            l_layout.shadowHeight = activity.viewShadowHeight
            l_layout.shadowWidth = activity.viewShadowWidth
        }
    }
}