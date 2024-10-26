package com.example.submission_navigation

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitcompat.SplitCompatApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : SplitCompatApplication() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        // This ensures that SplitCompat is installed to support dynamic feature modules.
        SplitCompat.install(this)
    }
}