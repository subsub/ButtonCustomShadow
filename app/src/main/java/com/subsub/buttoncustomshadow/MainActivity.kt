package com.subsub.buttoncustomshadow

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOf(button1, button2, button3).onEach { it.setOnClickListener(this) }
    }

    override fun onClick(v: View?) {
        when (v!!) {
            button1 -> showToast("Button1 Clicked")
            button2 -> showToast("Button2 Clicked")
            button3 -> showToast("Button3 Clicked")
        }
    }

    private fun Activity.showToast(string: String) =
            Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}
