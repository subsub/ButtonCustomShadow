package com.subsub.buttoncustomshadow

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.GONE
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    var viewElevation: Float = 0f
    var viewShadowRadius: Float = 0f
    var viewXOffset: Float = 0f
    var viewYOffset: Float = 0f
    var viewShadowHeight: Float = 0f
    var viewShadowWidth: Float = 0f

    var listener: (() -> Unit)? = null

    var controlContainer: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controlContainer = control_container
        controlContainer?.visibility = GONE

        listOf(elevation,
                shadow_radius,
                x_offset,
                y_offset,
                shadow_height,
                shadow_width)
                .onEach { it.setOnSeekBarChangeListener(this) }
                .onEach {
                    it.max = 100
                }
    }

    fun reset() {
        listener = null
        viewElevation = 0f
        viewShadowRadius = 0f
        viewXOffset = 0f
        viewYOffset = 0f
        viewShadowHeight = 0f
        viewShadowWidth = 0f
    }

    fun applyCurrent(elevation: Float, shadow_radius: Float, x_offset: Float, y_offset: Float, shadow_height: Float, shadow_width: Float) {
        viewElevation = elevation
        viewShadowRadius = shadow_radius
        viewXOffset = x_offset
        viewYOffset = y_offset
        viewShadowHeight = shadow_height
        viewShadowWidth = shadow_width
        this.elevation.progress = viewElevation.toInt()
        this.shadow_radius.progress = viewShadowRadius.toInt()
        this.x_offset.progress = viewXOffset.toInt()
        this.y_offset.progress = viewYOffset.toInt()
        this.shadow_height.progress = (viewShadowHeight * 100).toInt()
        this.shadow_width.progress = (viewShadowWidth * 100).toInt()
    }

    override fun onProgressChanged(view: SeekBar?, progress: Int, p2: Boolean) {
        if (view != null) with(view) {
            when (id) {
                R.id.elevation -> viewElevation = progress.toFloat()
                R.id.shadow_radius -> viewShadowRadius = progress.toFloat()
                R.id.x_offset -> viewXOffset = progress.toFloat()
                R.id.y_offset -> viewYOffset = progress.toFloat()
                R.id.shadow_height -> viewShadowHeight = progress.toFloat() / 100f
                R.id.shadow_width -> viewShadowWidth = progress.toFloat() / 100f
            }
        }
        listener?.invoke()
    }

    override fun onStartTrackingTouch(view: SeekBar?) {

    }

    override fun onStopTrackingTouch(view: SeekBar?) {

    }
}
