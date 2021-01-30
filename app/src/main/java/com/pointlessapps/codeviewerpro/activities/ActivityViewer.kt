package com.pointlessapps.codeviewerpro.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pointlessapps.codeviewerpro.databinding.ActivityViewerBinding
import com.pointlessapps.codeviewerpro.utils.Utils
import com.pointlessapps.codeviewerpro.viewModels.ViewModelViewer

class ActivityViewer : AppCompatActivity() {

	companion object {
		const val KEY_FILENAME = "filename"
		const val KEY_SOURCE_CODE = "sourceCode"
		const val KEY_EXTENSION = "extension"
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val binding = ActivityViewerBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val viewModel = ViewModelProvider(this, Utils.getViewModelFactory(this, binding))
			.get(ViewModelViewer::class.java)

		viewModel.setFilename(intent.getStringExtra(KEY_FILENAME) ?: return)
		viewModel.setSourceCode(
			intent.getStringExtra(KEY_SOURCE_CODE) ?: return,
			intent.getStringExtra(KEY_EXTENSION) ?: return
		)
	}
}