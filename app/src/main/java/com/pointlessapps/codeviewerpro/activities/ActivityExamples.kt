package com.pointlessapps.codeviewerpro.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pointlessapps.codeviewerpro.databinding.ActivityExamplesBinding
import com.pointlessapps.codeviewerpro.utils.Utils
import com.pointlessapps.codeviewerpro.viewModels.ViewModelExamples

class ActivityExamples : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val binding = ActivityExamplesBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val viewModel = ViewModelProvider(this, Utils.getViewModelFactory(this, binding))
			.get(ViewModelExamples::class.java)

		viewModel.prepareExamplesList()
		viewModel.onShowExampleListener = {
			startActivity(
				Intent(this, ActivityExample::class.java)
					.putExtra(ActivityExample.KEY_EXAMPLE, it)
			)
		}
	}
}