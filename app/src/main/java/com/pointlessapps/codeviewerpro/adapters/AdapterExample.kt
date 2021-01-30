package com.pointlessapps.codeviewerpro.adapters

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pointlessapps.codeviewerpro.databinding.ItemExampleBinding
import com.pointlessapps.codeviewerpro.models.Example

class AdapterExample(data: List<Example>) :
	AdapterBase<Example, ItemExampleBinding>(data.toMutableList(), ItemExampleBinding::class.java) {

	override fun onBind(root: ItemExampleBinding, position: Int) {
		root.textExample.text = list[position].title
		root.listLanguages.apply {
			layoutManager = LinearLayoutManager(root.root.context, RecyclerView.HORIZONTAL, false)
			adapter = AdapterExampleLanguages(list[position].languages)
		}
	}
}