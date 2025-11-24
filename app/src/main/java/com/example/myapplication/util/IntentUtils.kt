package com.example.myapplication.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import timber.log.Timber

object IntentUtils {

    fun Context.share(
        type: String = "text/plain",
        text: String = ""
    ) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            setType(type)
            putExtra(Intent.EXTRA_TEXT, text)
        }
        val chooserIntent = Intent.createChooser(intent, null)
        startActivity(chooserIntent)
    }

    fun Context.redirectToApp(link: String, type: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        try {
            when (type) {
                AppType.YOUTUBE.value -> intent.setPackage("com.google.android.youtube")
                AppType.INSTAGRAM.value -> intent.setPackage("com.instagram.android")
            }
            startActivity(intent)
        } catch (e: Exception) {
            Timber.e("share redirect fail: $e")
        }
    }
}

fun Context.openAppSetting() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        .apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            data = Uri.fromParts("package", packageName, null)
        }
    startActivity(intent)
}

enum class AppType(val value: String) {
    YOUTUBE("type_youtube"),
    INSTAGRAM("type_instagram"),
}