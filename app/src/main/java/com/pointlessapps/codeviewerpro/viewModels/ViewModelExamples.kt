package com.pointlessapps.codeviewerpro.viewModels

import android.app.Activity
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pointlessapps.codeviewerpro.adapters.AdapterExample
import com.pointlessapps.codeviewerpro.databinding.ActivityExamplesBinding
import com.pointlessapps.codeviewerpro.models.Example
import com.pointlessapps.codeviewerpro.utils.ExamplesData

class ViewModelExamples(private val activity: Activity, private val binding: ActivityExamplesBinding) :
    AndroidViewModel(activity.application) {

    var onShowExampleListener: ((Example) -> Unit)? = null

    private val data = ExamplesData.get(activity)

    fun prepareExamplesList() {
        binding.listExamples.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = AdapterExample(data).apply {
                onClickListener = {
                    onShowExampleListener?.invoke(data[it])
                }
            }
        }
    }
}