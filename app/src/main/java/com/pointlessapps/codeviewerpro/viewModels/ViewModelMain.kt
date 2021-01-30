package com.pointlessapps.codeviewerpro.viewModels

import android.app.Activity
import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.AndroidViewModel
import com.pointlessapps.codeviewerpro.databinding.ActivityMainBinding
import com.pointlessapps.codeviewerpro.utils.Utils
import java.io.File

class ViewModelMain(private val activity: Activity, private val binding: ActivityMainBinding) :
    AndroidViewModel(activity.application) {

    companion object {
        const val PICK_FILE_RESULT_CODE = 1
    }

    var onFileChosenListener: ((String, String, String) -> Unit)? = null
    var onShowExamplesListener: (() -> Unit)? = null
    var onActivityResultListener: (Int, Int, Intent?) -> Boolean = lambda@{ req, res, data ->
        if (req == PICK_FILE_RESULT_CODE && res == Activity.RESULT_OK && data != null) {
            if (data.data == null) {
                return@lambda false
            }

            val path = Utils.getPath(activity, data.data!!) ?: return@lambda false
            val filename = Utils.getFilename(activity, data.data!!) ?: return@lambda false
            onFileChosenListener?.invoke(
                Utils.getFileContentFromPath(path),
                filename,
                Utils.getExtension(path)
            )

            return@lambda true
        }

        return@lambda false
    }

    fun prepareClickListeners() {
        binding.buttonOpenFile.setOnClickListener { clickedOpenFile() }
        binding.buttonShowExamples.setOnClickListener { clickedShowExamples() }
    }

    private fun clickedOpenFile() {
        startActivityForResult(
            activity,
            Intent.createChooser(Intent(Intent.ACTION_GET_CONTENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "*/*"
            }, "Choose a file"),
            PICK_FILE_RESULT_CODE,
            null
        )
    }

    private fun clickedShowExamples() = onShowExamplesListener?.invoke()
}