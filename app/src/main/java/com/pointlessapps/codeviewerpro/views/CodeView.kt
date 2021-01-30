package com.pointlessapps.codeviewerpro.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import com.pointlessapps.codeviewerpro.utils.spToPx
import kotlin.math.max
import kotlin.math.min

class CodeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

	private val minTextSize = 14f.spToPx(context)
	private val maxTextSize = 32f.spToPx(context)
	private var lineNumberPadding = 15f
	private var minVisibleNumbers = 2
	private val lineNumberPaint = TextPaint().also {
		it.set(paint)
		it.color = Color.GRAY
	}

	init {
		calculatePadding()
        setTextIsSelectable(true)
	}

	private fun calculatePadding() {
		val numberWidth = Rect().also { lineNumberPaint.getTextBounds("0", 0, 1, it) }.width()
		setPadding(
            (lineNumberPadding * 2 + minVisibleNumbers * numberWidth).toInt(),
            paddingTop,
            paddingRight,
            paddingBottom
        )
	}

	override fun onDraw(canvas: Canvas) {
		(0..lineCount).forEach {
			canvas.drawText(
                "${it + 1}",
                lineNumberPadding,
                (lineHeight * (it + 1)).toFloat() + paddingTop,
                lineNumberPaint
            )
		}
		super.onDraw(canvas)
	}

	override fun canScrollVertically(direction: Int) = false

	override fun isTextSelectable() = true

	override fun setTextSize(textSize: Float) {
		max(min(maxTextSize, textSize), minTextSize).also {
			setTextSize(TypedValue.COMPLEX_UNIT_PX, it)
			lineNumberPaint.textSize = it
			calculatePadding()
		}
	}
}