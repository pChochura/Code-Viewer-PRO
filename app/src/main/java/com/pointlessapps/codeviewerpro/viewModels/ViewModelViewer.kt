package com.pointlessapps.codeviewerpro.viewModels

import android.app.Activity
import androidx.lifecycle.AndroidViewModel
import com.pointlessapps.codeviewerpro.databinding.ActivityViewerBinding

class ViewModelViewer(activity: Activity, private val binding: ActivityViewerBinding) :
	AndroidViewModel(activity.application) {

	fun setSourceCode(source: String, extension: String) {
		binding.textSourceCode.setSource(source, extension)
	}

	fun setFilename(filename: String) {
		binding.textFilename.text = filename
	}
}