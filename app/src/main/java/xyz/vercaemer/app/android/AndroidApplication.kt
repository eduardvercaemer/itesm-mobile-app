package xyz.vercaemer.app.android

import android.app.Application
import android.util.Log
import xyz.vercaemer.app.CoreApplication
import xyz.vercaemer.app.DependencyManager

class AndroidApplication : Application() {
    lateinit var dependencyManager: DependencyManager

    override fun onCreate() {
        super.onCreate()
        Log.d("AndroidApplication", "onCreate")
        dependencyManager = CoreApplication.Builder().start()
    }
}