package com.pointlessapps.codeviewerpro.adapters

import com.pointlessapps.codeviewerpro.databinding.ItemExampleSnippetBinding
import com.pointlessapps.codeviewerpro.models.Example

class AdapterExampleSnippets(data: List<Example.CodeSnippet>) :
    AdapterBase<Example.CodeSnippet, ItemExampleSnippetBinding>(
        data.toMutableList(),
        ItemExampleSnippetBinding::class.java
    ) {

    override fun onBind(root: ItemExampleSnippetBinding, position: Int) {
        root.textTitle.text = list[position].filename
        root.textSourceCode.setSource(list[position].source, list[position].extension)
    }
}
