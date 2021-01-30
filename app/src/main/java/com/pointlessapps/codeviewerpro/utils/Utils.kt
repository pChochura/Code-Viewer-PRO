package com.pointlessapps.codeviewerpro.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.io.File
import java.util.*


object Utils {

    @Suppress("UNCHECKED_CAST")
    fun getViewModelFactory(activity: AppCompatActivity, vararg args: Any) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>) =
                modelClass.constructors.first().newInstance(activity, *args) as T
        }

    fun getPath(context: Context, uri: Uri) =
        when (uri.scheme?.toLowerCase(Locale.getDefault())) {
            "content" -> runCatching {
                context.contentResolver.query(uri, arrayOf("_data"), null, null, null)?.let {
                    val index = it.getColumnIndex("_data")
                    if (index != -1 && it.moveToFirst()) {
                        it.getString(index)
                    } else {
                        null
                    }
                }
            }.getOrNull()
            "file" -> uri.path
            else -> null
        }

    fun getExtension(path: String) =
        path.substringAfterLast(".")

    fun getFilename(context: Context, uri: Uri) =
        context.contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)
            ?.let {
                val index = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (index != -1 && it.moveToFirst()) {
                    it.getString(index)
                } else {
                    null
                }
            }

    fun getFileContentFromPath(path: String) = File(path).readText()

    @ColorInt
    fun getLanguageColor(context: Context, language: String) =
        context.resources.getIdentifier("color_$language", "color", context.packageName)
            .takeIf { it != 0 }?.let { ContextCompat.getColor(context, it) } ?: 0
}

fun Float.spToPx(context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        context.resources.displayMetrics
    )
}

fun Float.dpToPx(context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        context.resources.displayMetrics
    )
}