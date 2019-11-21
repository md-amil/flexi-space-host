package com.example.myapplication.utilities

import android.app.Application
import android.util.Log
import com.jakewharton.threetenabp.AndroidThreeTen

class App: Application() {
    companion object{
        lateinit var loginPrefs:SheredPref
    }
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        Log.d("create","hello world")
        loginPrefs = SheredPref(applicationContext,"loginPreferences")
    }
}