package com.pointlessapps.codeviewerpro.models

import android.os.Parcelable
import androidx.annotation.ColorInt
import kotlinx.parcelize.Parcelize

@Parcelize
data class Example(
    val title: String,
    val languages: List<Language>,
    val codeSnippets: List<CodeSnippet>
) : Parcelable {
	@Parcelize
	data class Language(val name: String, @ColorInt val color: Int) : Parcelable

	@Parcelize
	data class CodeSnippet(val filename: String, val source: String, val extension: String) :
		Parcelable
}