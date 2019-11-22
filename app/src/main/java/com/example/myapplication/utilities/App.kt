package com.example.myapplication.utilities

import android.app.Application
import android.util.Log
import com.jakewharton.threetenabp.AndroidThreeTen

class App: Application() {
    companion object{
        lateinit var loginPrefs:SheredPref
    }
    override fun onCreate() {
        Log.d("create,",this::class.java.toString())
        super.onCreate()
        AndroidThreeTen.init(this)
        loginPrefs = SheredPref(applicationContext,"loginPreferences")
    }
}