package com.pointlessapps.codeviewerpro.adapters

import com.pointlessapps.codeviewerpro.databinding.ItemExampleLanguageBinding
import com.pointlessapps.codeviewerpro.models.Example

class AdapterExampleLanguages(data: List<Example.Language>) :
	AdapterBase<Example.Language, ItemExampleLanguageBinding>(
		data.toMutableList(),
		ItemExampleLanguageBinding::class.java
	) {

	override fun onBind(root: ItemExampleLanguageBinding, position: Int) {
		root.cardLanguage.setCardBackgroundColor(list[position].color)
		root.textLanguage.text = list[position].name
	}
}