package com.pointlessapps.codeviewerpro.views

import android.annotation.SuppressLint
import android.content.Context
import android.text.Html
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import com.pointlessapps.codeviewerpro.databinding.ViewCodeLayoutBinding
import io.github.kbiakov.codeview.highlight.CodeHighlighter
import io.github.kbiakov.codeview.highlight.ColorTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CodeViewLayout @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {

	private val binding = ViewCodeLayoutBinding.inflate(LayoutInflater.from(context), this, true)

	private val scaleGestureDetector = ScaleGestureDetector(
		context,
		object : ScaleGestureDetector.OnScaleGestureListener {
			override fun onScaleBegin(detector: ScaleGestureDetector) = true
			override fun onScaleEnd(detector: ScaleGestureDetector?) = Unit
			override fun onScale(detector: ScaleGestureDetector): Boolean {
				binding.codeView.textSize = binding.codeView.textSize * detector.scaleFactor
				return true
			}
		})

	@SuppressLint("ClickableViewAccessibility")
	override fun onTouchEvent(event: MotionEvent) =
		event.pointerCount.takeIf { it > 1 }?.let { scaleGestureDetector.onTouchEvent(event) }
			?: super.onTouchEvent(event)

	fun setSource(source: String, language: String) {
		binding.progressView.isVisible = true
		binding.codeView.isVisible = false
		GlobalScope.launch(Dispatchers.IO) {
			val highlightText = CodeHighlighter.highlight(
				language,
				source,
				ColorTheme.MONOKAI.theme()
			).replace("\n", "<br/>")
				.replace("[ ]{2}", "&nbsp; ")

			GlobalScope.launch(Dispatchers.Main) {
				binding.progressView.isVisible = false
				binding.codeView.isVisible = true
				if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
					binding.codeView.text = Html.fromHtml(
						highlightText,
						Html.FROM_HTML_MODE_COMPACT
					)
				} else {
					binding.codeView.text = Html.fromHtml(highlightText)
				}
			}
		}
	}
}