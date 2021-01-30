package com.pointlessapps.codeviewerpro.utils

import android.content.Context
import com.pointlessapps.codeviewerpro.models.Example
import java.nio.charset.StandardCharsets

object ExamplesData {

	private const val BASE_PATH = "codeSnippets"

	fun get(context: Context): List<Example> {
		val folders = context.assets.list(BASE_PATH) ?: return emptyList()
		val examples = mutableListOf<Example>()
		folders.forEach { folderName ->
			val files = context.assets.list("$BASE_PATH/$folderName") ?: emptyArray()
			val codeSnippets = mutableListOf<Example.CodeSnippet>()
			val languages = mutableSetOf<String>()
			files.forEach {
				val inputStream = context.assets.open("$BASE_PATH/$folderName/$it")
				val extension = Utils.getExtension(it)
				languages.add(extension)
				codeSnippets.add(
					Example.CodeSnippet(
						filename = it,
						source = String(inputStream.readBytes(), StandardCharsets.UTF_8),
						extension = extension
					)
				)
				inputStream.close()
			}
			examples.add(
				Example(
					title = folderName,
					languages = languages.map {
						Example.Language(
							it,
							Utils.getLanguageColor(context, it)
						)
					},
					codeSnippets = codeSnippets
				)
			)
		}

		return examples
	}
}