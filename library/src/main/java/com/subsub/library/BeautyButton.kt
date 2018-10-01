package com.subsub.library

import android.content.Context
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.GradientDrawable.RECTANGLE
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.Gravity

/**
 * Created by subkhansarif on 26/09/18
 **/

class BeautyButton : AppCompatTextView {

    private var centerX: Float = 100f
    private var centerY: Float = 100f

    private lateinit var paint: Paint
    private lateinit var mainRect: Rect
    private lateinit var shadowRect: Rect

    private lateinit var topLeftCornerRect: Rect
    private lateinit var topLeftCornerGradient: GradientDrawable

    private lateinit var topRect: Rect
    private lateinit var topGradient: GradientDrawable

    private lateinit var topRightCornerRect: Rect
    private lateinit var topRightCornerGradient: GradientDrawable

    private lateinit var rightRect: Rect
    private lateinit var rightGradient: GradientDrawable

    private lateinit var bottomRightCornerRect: Rect
    private lateinit var bottomRightCornerGradient: GradientDrawable

    private lateinit var bottomRect: Rect
    private lateinit var bottomGradient: GradientDrawable

    private lateinit var bottomLeftCornerRect: Rect
    private lateinit var bottomLeftCornerGradient: GradientDrawable

    private lateinit var leftRect: Rect
    private lateinit var leftGradient: GradientDrawable

    private var myElevation = 0f
    private var customShadowColor = 0
    private var customShadowRadius = 100f
    private var xOffset = 0f
    private var yOffset = 0f
    private var shadowHeight = 1f
    private var shadowWidth = 1f

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        getAttributes(attributeSet)
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttrs: Int) : super(context, attributeSet, defStyleAttrs) {
        getAttributes(attributeSet)
        init()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = (w / 2).toFloat()
        centerY = (h / 2).toFloat()
        mainRect = Rect(0, 0, w, h)

        gravity = Gravity.CENTER

        with(customShadowRadius) {
            topLeftCornerRect = Rect(0, 0, toInt(), toInt())
            topRect = Rect(0, 0, (width - customShadowRadius * 2 + myElevation * 2
                    - ((1 - shadowWidth) * width)).toInt(), toInt())

            topRightCornerRect = Rect(0, 0, toInt(), toInt())
            rightRect = Rect(0, 0, toInt(), (height - customShadowRadius * 2 + myElevation * 2
                    - (1 - shadowHeight) * height).toInt())

            bottomRightCornerRect = Rect(0, 0, toInt(), toInt())
            bottomRect = Rect(0, 0, (width - customShadowRadius * 2 + myElevation * 2
                    - ((1 - shadowWidth) * width)).toInt(), toInt())

            bottomLeftCornerRect = Rect(0, 0, toInt(), toInt())
            leftRect = Rect(0, 0, toInt(), (height - customShadowRadius * 2 + myElevation * 2
                    - (1 - shadowHeight) * height).toInt())
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.also {
            enableOutsideBound(it)

            // draw shadow
            drawTopLeftCorner(it)
            drawTop(it)
            drawTopRightCorner(it)
            drawRight(it)
            drawBottomRightCorner(it)
            drawBottom(it)
            drawBottomLeftCorner(it)
            drawLeft(it)

            // draw main content
            drawButtonBackground(it)
        }
        super.onDraw(canvas)
    }

    private fun init() {

        paint = Paint().apply {
            color = Color.BLUE
            style = Paint.Style.FILL_AND_STROKE
        }

        mainRect = Rect()
        shadowRect = Rect()

        initGradient()
    }

    private fun getAttributes(attr: AttributeSet) {

        myElevation = elevation
        elevation = 0f

        with(context.obtainStyledAttributes(attr, R.styleable.BeautyButton)) {
            customShadowRadius = getDimension(R.styleable.BeautyButton_shadowRadius,
                    myElevation + (2 * resources.displayMetrics.density))

            if (customShadowRadius <= myElevation)
                customShadowRadius = myElevation + (2 * resources.displayMetrics.density)

            customShadowColor = getColor(R.styleable.BeautyButton_shadowColor,
                    ContextCompat.getColor(context, R.color.colorDefaultShadowColor))

            xOffset = getFloat(R.styleable.BeautyButton_shadowXOffset, 0f)
            yOffset = getFloat(R.styleable.BeautyButton_shadowYOffset, 0f)

            shadowHeight = getFloat(R.styleable.BeautyButton_shadowPercentHeight, 1f)
            shadowWidth = getFloat(R.styleable.BeautyButton_shadowPercentWidth, 1f)

            recycle()
        }

        background = background ?: ContextCompat.getDrawable(context, R.drawable.default_background)
    }

    override fun performClick(): Boolean {
        // TODO: Add shadow elevation changes on click
        return super.performClick()
    }

    private fun initGradient() {
        initTopLeftCorner()
        initTop()
        initTopRightCorner()
        initRight()
        initBottomRightCorner()
        initBottom()
        initBottomLeftCorner()
        initLeft()
    }

    private fun initTopLeftCorner() {
        topLeftCornerGradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(customShadowColor, Color.TRANSPARENT)).apply {
            shape = RECTANGLE
            gradientType = GradientDrawable.RADIAL_GRADIENT
            gradientRadius = customShadowRadius
            setGradientCenter(1f, 1f)
        }
    }

    private fun initTop() {
        topGradient = GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
                intArrayOf(customShadowColor, Color.TRANSPARENT)).apply {
            shape = RECTANGLE
            gradientType = GradientDrawable.LINEAR_GRADIENT
            gradientRadius = customShadowRadius
        }
    }

    private fun initTopRightCorner() {
        topRightCornerGradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(customShadowColor, Color.TRANSPARENT)).apply {
            shape = RECTANGLE
            gradientType = GradientDrawable.RADIAL_GRADIENT
            gradientRadius = customShadowRadius
            setGradientCenter(0f, 1f)
        }
    }

    private fun initRight() {
        rightGradient = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(customShadowColor, Color.TRANSPARENT)).apply {
            shape = RECTANGLE
            gradientType = GradientDrawable.LINEAR_GRADIENT
            gradientRadius = customShadowRadius
        }
    }

    private fun initBottomRightCorner() {
        bottomRightCornerGradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(customShadowColor, Color.TRANSPARENT)).apply {
            shape = RECTANGLE
            gradientType = GradientDrawable.RADIAL_GRADIENT
            gradientRadius = customShadowRadius
            setGradientCenter(0f, 0f)
        }
    }

    private fun initBottom() {
        bottomGradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(customShadowColor, Color.TRANSPARENT)).apply {
            shape = RECTANGLE
            gradientType = GradientDrawable.LINEAR_GRADIENT
            gradientRadius = customShadowRadius
        }
    }

    private fun initBottomLeftCorner() {
        bottomLeftCornerGradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(customShadowColor, Color.TRANSPARENT)).apply {
            shape = RECTANGLE
            gradientType = GradientDrawable.RADIAL_GRADIENT
            gradientRadius = customShadowRadius
            setGradientCenter(1f, 0f)
        }
    }

    private fun initLeft() {
        leftGradient = GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT,
                intArrayOf(customShadowColor, Color.TRANSPARENT)).apply {
            shape = RECTANGLE
            gradientType = GradientDrawable.LINEAR_GRADIENT
            gradientRadius = customShadowRadius
        }
    }

    private fun enableOutsideBound(canvas: Canvas) = with(canvas) {
        getClipBounds(shadowRect)
        shadowRect.inset((-myElevation - xOffset).toInt(), (-myElevation - yOffset).toInt())
        clipRect(shadowRect, Region.Op.REPLACE)
    }

    private fun drawTopLeftCorner(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2

        with(canvas) {
            save()

            topLeftCornerGradient.bounds = topLeftCornerRect

            translate(-myElevation + xOffset + percentX, -myElevation + yOffset + percentY)

            topLeftCornerGradient.draw(this)

            restore()
        }
    }

    private fun drawTop(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2

        with(canvas) {
            save()

            topGradient.bounds = topRect

            translate(customShadowRadius - myElevation + xOffset + percentX,
                    -myElevation + yOffset + percentY)

            topGradient.draw(this)

            restore()
        }
    }

    private fun drawTopRightCorner(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2

        with(canvas) {
            save()

            topRightCornerGradient.bounds = topRightCornerRect

            translate(width - customShadowRadius + myElevation + xOffset - percentX,
                    -myElevation + yOffset + percentY)

            topRightCornerGradient.draw(this)

            restore()
        }
    }

    private fun drawRight(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2

        with(canvas) {
            save()

            rightGradient.bounds = rightRect

            translate(width - customShadowRadius + myElevation + xOffset - percentX,
                    customShadowRadius - myElevation + yOffset + percentY)

            rightGradient.draw(this)

            restore()
        }
    }

    private fun drawBottomRightCorner(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2

        with(canvas) {
            save()

            bottomRightCornerGradient.bounds = bottomRightCornerRect

            translate(width - customShadowRadius + myElevation + xOffset - percentX,
                    height - customShadowRadius + myElevation + yOffset - percentY)

            bottomRightCornerGradient.draw(this)

            restore()
        }
    }

    private fun drawBottom(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2

        with(canvas) {
            save()

            bottomGradient.bounds = bottomRect

            translate(customShadowRadius - myElevation + xOffset + percentX,
                    height - customShadowRadius + myElevation + yOffset - percentY)

            bottomGradient.draw(this)

            restore()
        }
    }

    private fun drawBottomLeftCorner(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2

        with(canvas) {
            save()

            bottomLeftCornerGradient.bounds = bottomLeftCornerRect

            translate(-myElevation + xOffset + percentX,
                    height - customShadowRadius + myElevation + yOffset - percentY)

            bottomLeftCornerGradient.draw(this)

            restore()
        }
    }

    private fun drawLeft(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2

        with(canvas) {
            save()

            leftGradient.bounds = leftRect

            translate(-myElevation + xOffset + percentX,
                    customShadowRadius - myElevation + yOffset + percentY)

            leftGradient.draw(this)

            restore()
        }
    }

    private fun drawButtonBackground(canvas: Canvas) {
        with(canvas) {
            save()
            background.draw(this)
            restore()
        }
    }
}