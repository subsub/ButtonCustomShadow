package com.subsub.library

import android.content.Context
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.GradientDrawable.RECTANGLE
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

    private var myElevation: Float = 0f
    private var customShadowColor: Int = 0
    private var customShadowRadius: Float = 100f
    private var xOffset: Float = 0f
    private var yOffset: Float = 0f
    private var shadowHeight: Float = 1f
    private var shadowWidth: Float = 1f

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

        topLeftCornerRect = Rect(0, 0, customShadowRadius.toInt(), customShadowRadius.toInt())
        topRect = Rect(0, 0, (width - customShadowRadius * 2 + myElevation * 2 - ((1 - shadowWidth) * width)).toInt(), customShadowRadius.toInt())
        topRightCornerRect = Rect(0, 0, customShadowRadius.toInt(), customShadowRadius.toInt())
        rightRect = Rect(0, 0, customShadowRadius.toInt(), (height - customShadowRadius * 2 + myElevation * 2 - (1 - shadowHeight) * height).toInt())
        bottomRightCornerRect = Rect(0, 0, customShadowRadius.toInt(), customShadowRadius.toInt())
        bottomRect = Rect(0, 0, (width - customShadowRadius * 2 + myElevation * 2 - ((1 - shadowWidth) * width)).toInt(), customShadowRadius.toInt())
        bottomLeftCornerRect = Rect(0, 0, customShadowRadius.toInt(), customShadowRadius.toInt())
        leftRect = Rect(0, 0, customShadowRadius.toInt(), (height - customShadowRadius * 2 + myElevation * 2 - (1 - shadowHeight) * height).toInt())

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

        paint = Paint()
        mainRect = Rect()
        shadowRect = Rect()

        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL_AND_STROKE

        initGradient()
    }

    private fun getAttributes(attr: AttributeSet) {
        val styles = context.obtainStyledAttributes(attr, R.styleable.BeautyButton)

        myElevation = elevation
        elevation = 0f
        customShadowRadius = styles.getDimension(R.styleable.BeautyButton_shadowRadius, myElevation + (2 * resources.displayMetrics.density))
        if (customShadowRadius <= myElevation) customShadowRadius = myElevation + (2 * resources.displayMetrics.density)
        customShadowColor = styles.getColor(R.styleable.BeautyButton_shadowColor, resources.getColor(R.color.colorDefaultShadowColor))
        xOffset = styles.getFloat(R.styleable.BeautyButton_shadowXOffset, 0f)
        yOffset = styles.getFloat(R.styleable.BeautyButton_shadowYOffset, 0f)
        shadowHeight = styles.getFloat(R.styleable.BeautyButton_shadowPercentHeight, 1f)
        shadowWidth = styles.getFloat(R.styleable.BeautyButton_shadowPercentWidth, 1f)
        background = background ?: resources.getDrawable(R.drawable.default_background)

        styles.recycle()
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
        topLeftCornerGradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(customShadowColor, Color.TRANSPARENT))
        topLeftCornerGradient.shape = RECTANGLE
        topLeftCornerGradient.gradientType = GradientDrawable.RADIAL_GRADIENT
        topLeftCornerGradient.gradientRadius = customShadowRadius
        topLeftCornerGradient.setGradientCenter(1f, 1f)
    }

    private fun initTop() {
        topGradient = GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, intArrayOf(customShadowColor, Color.TRANSPARENT))
        topGradient.shape = RECTANGLE
        topGradient.gradientType = GradientDrawable.LINEAR_GRADIENT
        topGradient.gradientRadius = customShadowRadius
    }

    private fun initTopRightCorner() {
        topRightCornerGradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(customShadowColor, Color.TRANSPARENT))
        topRightCornerGradient.shape = RECTANGLE
        topRightCornerGradient.gradientType = GradientDrawable.RADIAL_GRADIENT
        topRightCornerGradient.gradientRadius = customShadowRadius
        topRightCornerGradient.setGradientCenter(0f, 1f)
    }

    private fun initRight() {
        rightGradient = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, intArrayOf(customShadowColor, Color.TRANSPARENT))
        rightGradient.shape = RECTANGLE
        rightGradient.gradientType = GradientDrawable.LINEAR_GRADIENT
        rightGradient.gradientRadius = customShadowRadius
    }

    private fun initBottomRightCorner() {
        bottomRightCornerGradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(customShadowColor, Color.TRANSPARENT))
        bottomRightCornerGradient.shape = RECTANGLE
        bottomRightCornerGradient.gradientType = GradientDrawable.RADIAL_GRADIENT
        bottomRightCornerGradient.gradientRadius = customShadowRadius
        bottomRightCornerGradient.setGradientCenter(0f, 0f)
    }

    private fun initBottom() {
        bottomGradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(customShadowColor, Color.TRANSPARENT))
        bottomGradient.shape = RECTANGLE
        bottomGradient.gradientType = GradientDrawable.LINEAR_GRADIENT
        bottomGradient.gradientRadius = customShadowRadius
    }

    private fun initBottomLeftCorner() {
        bottomLeftCornerGradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(customShadowColor, Color.TRANSPARENT))
        bottomLeftCornerGradient.shape = RECTANGLE
        bottomLeftCornerGradient.gradientType = GradientDrawable.RADIAL_GRADIENT
        bottomLeftCornerGradient.gradientRadius = customShadowRadius
        bottomLeftCornerGradient.setGradientCenter(1f, 0f)
    }

    private fun initLeft() {
        leftGradient = GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, intArrayOf(customShadowColor, Color.TRANSPARENT))
        leftGradient.shape = RECTANGLE
        leftGradient.gradientType = GradientDrawable.LINEAR_GRADIENT
        leftGradient.gradientRadius = customShadowRadius
    }

    private fun enableOutsideBound(canvas: Canvas) {
        canvas.getClipBounds(shadowRect)
        shadowRect.inset((-myElevation - xOffset).toInt(), (-myElevation - yOffset).toInt())
        canvas.clipRect(shadowRect, Region.Op.REPLACE)
    }

    private fun drawTopLeftCorner(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2
        canvas.save()
        topLeftCornerGradient.bounds = topLeftCornerRect
        canvas.translate(-myElevation + xOffset + percentX, -myElevation + yOffset + percentY)
        topLeftCornerGradient.draw(canvas)
        canvas.restore()
    }

    private fun drawTop(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2
        canvas.save()
        topGradient.bounds = topRect
        canvas.translate(customShadowRadius - myElevation + xOffset + percentX, -myElevation + yOffset + percentY)
        topGradient.draw(canvas)
        canvas.restore()
    }

    private fun drawTopRightCorner(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2
        canvas.save()
        topRightCornerGradient.bounds = topRightCornerRect
        canvas.translate(width - customShadowRadius + myElevation + xOffset - percentX, -myElevation + yOffset + percentY)
        topRightCornerGradient.draw(canvas)
        canvas.restore()
    }

    private fun drawRight(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2
        canvas.save()
        rightGradient.bounds = rightRect
        canvas.translate(width - customShadowRadius + myElevation + xOffset - percentX, customShadowRadius - myElevation + yOffset + percentY)
        rightGradient.draw(canvas)
        canvas.restore()
    }

    private fun drawBottomRightCorner(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2
        canvas.save()
        bottomRightCornerGradient.bounds = bottomRightCornerRect
        canvas.translate(width - customShadowRadius + myElevation + xOffset - percentX, height - customShadowRadius + myElevation + yOffset - percentY)
        bottomRightCornerGradient.draw(canvas)
        canvas.restore()
    }

    private fun drawBottom(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2
        canvas.save()
        bottomGradient.bounds = bottomRect
        canvas.translate(customShadowRadius - myElevation + xOffset + percentX, height - customShadowRadius + myElevation + yOffset - percentY)
        bottomGradient.draw(canvas)
        canvas.restore()
    }

    private fun drawBottomLeftCorner(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2
        canvas.save()
        bottomLeftCornerGradient.bounds = bottomLeftCornerRect
        canvas.translate(-myElevation + xOffset + percentX, height - customShadowRadius + myElevation + yOffset - percentY)
        bottomLeftCornerGradient.draw(canvas)
        canvas.restore()
    }

    private fun drawLeft(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2
        canvas.save()
        leftGradient.bounds = leftRect
        canvas.translate(-myElevation + xOffset + percentX, customShadowRadius - myElevation + yOffset + percentY)
        leftGradient.draw(canvas)
        canvas.restore()
    }

    private fun drawButtonBackground(canvas: Canvas) {
        canvas.save()
        background.draw(canvas)
        canvas.restore()
    }
}