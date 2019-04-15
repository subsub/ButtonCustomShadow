package com.subsub.buttoncustomshadow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.subsub.buttoncustomshadow.MainActivity
import com.subsub.buttoncustomshadow.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by subkhansarif on 14/11/18
 **/

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val activity = activity as MainActivity
        btn_button.setOnClickListener {
            activity.reset()
            activity.controlContainer?.visibility = VISIBLE
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_buttonFragment)
        }

        btn_layout.setOnClickListener {
            activity.reset()
            activity.controlContainer?.visibility = VISIBLE
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_layoutFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        val activity = activity as MainActivity
        activity.controlContainer?.visibility = GONE
    }
}