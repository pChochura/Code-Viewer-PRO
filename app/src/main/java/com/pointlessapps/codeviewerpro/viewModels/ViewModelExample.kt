package com.pointlessapps.codeviewerpro.viewModels

import android.app.Activity
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pointlessapps.codeviewerpro.adapters.AdapterExampleSnippets
import com.pointlessapps.codeviewerpro.databinding.ActivityExampleBinding
import com.pointlessapps.codeviewerpro.models.Example

class ViewModelExample(
    private val activity: Activity,
    private val binding: ActivityExampleBinding,
    private val example: Example
) : AndroidViewModel(activity.application) {

    fun prepareSnippetsList() {
        binding.listSnippets.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = AdapterExampleSnippets(example.codeSnippets)
            setItemViewCacheSize(example.codeSnippets.size)
        }
    }
}