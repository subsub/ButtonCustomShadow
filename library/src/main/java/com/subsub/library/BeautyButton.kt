package com.subsub.library

import android.content.Context
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.GradientDrawable.RECTANGLE
import android.util.AttributeSet
import android.view.View

/**
 * Created by subkhansarif on 26/09/18
 **/

class BeautyButton : View {

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
    private var cornerRadius: Float = 0f
    private var shadowColor: Int = 0
    private var shadowRadius: Float = 100f
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

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttrs: Int, defStyleRes: Int) : super(context, attributeSet, defStyleAttrs, defStyleRes) {
        getAttributes(attributeSet)
        init()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = (w / 2).toFloat()
        centerY = (h / 2).toFloat()
        mainRect = Rect(0, 0, w, h)

        topLeftCornerRect = Rect(0, 0, shadowRadius.toInt(), shadowRadius.toInt())
        topRect = Rect(0, 0, (width - shadowRadius * 2 + myElevation * 2 - ((1 - shadowWidth) * width)).toInt(), shadowRadius.toInt())
        topRightCornerRect = Rect(0, 0, shadowRadius.toInt(), shadowRadius.toInt())
        rightRect = Rect(0, 0, shadowRadius.toInt(), (height - shadowRadius * 2 + myElevation * 2 - (1 - shadowHeight) * height).toInt())
        bottomRightCornerRect = Rect(0, 0, shadowRadius.toInt(), shadowRadius.toInt())
        bottomRect = Rect(0, 0, (width - shadowRadius * 2 + myElevation * 2 - ((1 - shadowWidth) * width)).toInt(), shadowRadius.toInt())
        bottomLeftCornerRect = Rect(0, 0, shadowRadius.toInt(), shadowRadius.toInt())
        leftRect = Rect(0, 0, shadowRadius.toInt(), (height - shadowRadius * 2 + myElevation * 2 - (1 - shadowHeight) * height).toInt())
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

        cornerRadius = styles.getDimension(R.styleable.BeautyButton_cornerRadius, 0f)
        shadowRadius = styles.getDimension(R.styleable.BeautyButton_shadowRadius, 0f)
        myElevation = elevation
        if (shadowRadius < elevation) shadowRadius = elevation
        elevation = 0f
        shadowColor = styles.getColor(R.styleable.BeautyButton_shadowColor, Color.GRAY)
        xOffset = styles.getFloat(R.styleable.BeautyButton_shadowXOffset, 0f)
        yOffset = styles.getFloat(R.styleable.BeautyButton_shadowYOffset, 0f)
        shadowHeight = styles.getFloat(R.styleable.BeautyButton_shadowPercentHeight, 1f)
        shadowWidth = styles.getFloat(R.styleable.BeautyButton_shadowPercentWidth, 1f)

        styles.recycle()
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
        topLeftCornerGradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(shadowColor, Color.TRANSPARENT))
        topLeftCornerGradient.shape = RECTANGLE
        topLeftCornerGradient.gradientType = GradientDrawable.RADIAL_GRADIENT
        topLeftCornerGradient.gradientRadius = shadowRadius
        topLeftCornerGradient.setGradientCenter(1f, 1f)
    }

    private fun initTop() {
        topGradient = GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, intArrayOf(shadowColor, Color.TRANSPARENT))
        topGradient.shape = RECTANGLE
        topGradient.gradientType = GradientDrawable.LINEAR_GRADIENT
        topGradient.gradientRadius = shadowRadius
    }

    private fun initTopRightCorner() {
        topRightCornerGradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(shadowColor, Color.TRANSPARENT))
        topRightCornerGradient.shape = RECTANGLE
        topRightCornerGradient.gradientType = GradientDrawable.RADIAL_GRADIENT
        topRightCornerGradient.gradientRadius = shadowRadius
        topRightCornerGradient.setGradientCenter(0f, 1f)
    }

    private fun initRight() {
        rightGradient = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, intArrayOf(shadowColor, Color.TRANSPARENT))
        rightGradient.shape = RECTANGLE
        rightGradient.gradientType = GradientDrawable.LINEAR_GRADIENT
        rightGradient.gradientRadius = shadowRadius
    }

    private fun initBottomRightCorner() {
        bottomRightCornerGradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(shadowColor, Color.TRANSPARENT))
        bottomRightCornerGradient.shape = RECTANGLE
        bottomRightCornerGradient.gradientType = GradientDrawable.RADIAL_GRADIENT
        bottomRightCornerGradient.gradientRadius = shadowRadius
        bottomRightCornerGradient.setGradientCenter(0f, 0f)
    }

    private fun initBottom() {
        bottomGradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(shadowColor, Color.TRANSPARENT))
        bottomGradient.shape = RECTANGLE
        bottomGradient.gradientType = GradientDrawable.LINEAR_GRADIENT
        bottomGradient.gradientRadius = shadowRadius
    }

    private fun initBottomLeftCorner() {
        bottomLeftCornerGradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(shadowColor, Color.TRANSPARENT))
        bottomLeftCornerGradient.shape = RECTANGLE
        bottomLeftCornerGradient.gradientType = GradientDrawable.RADIAL_GRADIENT
        bottomLeftCornerGradient.gradientRadius = shadowRadius
        bottomLeftCornerGradient.setGradientCenter(1f, 0f)
    }

    private fun initLeft() {
        leftGradient = GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, intArrayOf(shadowColor, Color.TRANSPARENT))
        leftGradient.shape = RECTANGLE
        leftGradient.gradientType = GradientDrawable.LINEAR_GRADIENT
        leftGradient.gradientRadius = shadowRadius
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
        canvas.translate(shadowRadius - myElevation + xOffset + percentX, -myElevation + yOffset + percentY)
        topGradient.draw(canvas)
        canvas.restore()
    }

    private fun drawTopRightCorner(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2
        canvas.save()
        topRightCornerGradient.bounds = topRightCornerRect
        canvas.translate(width - shadowRadius + myElevation + xOffset - percentX, -myElevation + yOffset + percentY)
        topRightCornerGradient.draw(canvas)
        canvas.restore()
    }

    private fun drawRight(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2
        canvas.save()
        rightGradient.bounds = rightRect
        canvas.translate(width - shadowRadius + myElevation + xOffset - percentX, shadowRadius - myElevation + yOffset + percentY)
        rightGradient.draw(canvas)
        canvas.restore()
    }

    private fun drawBottomRightCorner(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2
        canvas.save()
        bottomRightCornerGradient.bounds = bottomRightCornerRect
        canvas.translate(width - shadowRadius + myElevation + xOffset - percentX, height - shadowRadius + myElevation + yOffset - percentY)
        bottomRightCornerGradient.draw(canvas)
        canvas.restore()
    }

    private fun drawBottom(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2
        canvas.save()
        bottomGradient.bounds = bottomRect
        canvas.translate(shadowRadius - myElevation + xOffset + percentX, height - shadowRadius + myElevation + yOffset - percentY)
        bottomGradient.draw(canvas)
        canvas.restore()
    }

    private fun drawBottomLeftCorner(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2
        canvas.save()
        bottomLeftCornerGradient.bounds = bottomLeftCornerRect
        canvas.translate(-myElevation + xOffset + percentX, height - shadowRadius + myElevation + yOffset - percentY)
        bottomLeftCornerGradient.draw(canvas)
        canvas.restore()
    }

    private fun drawLeft(canvas: Canvas) {
        val percentX = (1 - shadowWidth) * width / 2
        val percentY = (1 - shadowHeight) * height / 2
        canvas.save()
        leftGradient.bounds = leftRect
        canvas.translate(-myElevation + xOffset + percentX, shadowRadius - myElevation + yOffset + percentY)
        leftGradient.draw(canvas)
        canvas.restore()
    }

    private fun drawButtonBackground(canvas: Canvas) {
        canvas.save()
        background.draw(canvas)
        canvas.restore()
    }

}