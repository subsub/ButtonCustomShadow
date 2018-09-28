package com.subsub.buttoncustomshadow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            Toast.makeText(this, "Button1 Clicked", Toast.LENGTH_SHORT).show()
        }
        button2.setOnClickListener {
            Toast.makeText(this, "Button2 Clicked", Toast.LENGTH_SHORT).show()
        }
        button3.setOnClickListener {
            Toast.makeText(this, "Button3 Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
