package com.pointlessapps.codeviewerpro.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pointlessapps.codeviewerpro.databinding.ActivityExampleBinding
import com.pointlessapps.codeviewerpro.models.Example
import com.pointlessapps.codeviewerpro.utils.Utils
import com.pointlessapps.codeviewerpro.viewModels.ViewModelExample

class ActivityExample : AppCompatActivity() {

	companion object {
		const val KEY_EXAMPLE = "example"
	}

	@Suppress("CAST_NEVER_SUCCEEDS")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val binding = ActivityExampleBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val example = intent.getParcelableExtra<Example>(KEY_EXAMPLE) ?: return
		val viewModel = ViewModelProvider(this, Utils.getViewModelFactory(this, binding, example))
			.get(ViewModelExample::class.java)

		viewModel.prepareSnippetsList()
	}
}