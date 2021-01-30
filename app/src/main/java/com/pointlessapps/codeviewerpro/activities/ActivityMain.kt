package com.pointlessapps.codeviewerpro.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pointlessapps.codeviewerpro.databinding.ActivityMainBinding
import com.pointlessapps.codeviewerpro.utils.Utils
import com.pointlessapps.codeviewerpro.viewModels.ViewModelMain
import io.github.kbiakov.codeview.classifier.CodeProcessor

class ActivityMain : AppCompatActivity() {

    private lateinit var viewModel: ViewModelMain

    init {
        CodeProcessor.init(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, Utils.getViewModelFactory(this, binding))
            .get(ViewModelMain::class.java)

        viewModel.prepareClickListeners()
        viewModel.onFileChosenListener = { content, filename, extension ->
            startActivity(
                Intent(this, ActivityViewer::class.java)
                    .putExtra(ActivityViewer.KEY_SOURCE_CODE, content)
                    .putExtra(ActivityViewer.KEY_FILENAME, filename)
                    .putExtra(ActivityViewer.KEY_EXTENSION, extension)
            )
        }
        viewModel.onShowExamplesListener = {
            startActivity(Intent(this, ActivityExamples::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!viewModel.onActivityResultListener(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}