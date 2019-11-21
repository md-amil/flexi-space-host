package com.example.myapplication.utilities

import android.content.Context
import android.content.SharedPreferences

class SheredPref(context: Context, fileName:String) {
    val defToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1ZDliOGM2MDJkZWZkOTQ4Y2I3Y2RjNTMiLCJpYXQiOjE1NzA0NzUxMDQyNTB9.VSIGHvoZbZFGlpvcKVASLd3JGlLzmgwr9DlSdruyjsU"
    private val PREFS_FILENAME = fileName
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME,0)
    private val IS_LOGGED_IN = "isLoggedIn"
    private val AUTH_TOKEN = null
    val USER_EMAIL = "userEmail"

    var isLoggedIn:Boolean
        get() = prefs.getBoolean(IS_LOGGED_IN,false)
        set(value) = prefs.edit().putBoolean(IS_LOGGED_IN,value).apply()

    var authToken:String?
        get() = prefs.getString(AUTH_TOKEN,"")
        set(value) = prefs.edit().putString(AUTH_TOKEN,value).apply()
    var userEmail:String?
        get() = prefs.getString(USER_EMAIL,"")
        set(value) = prefs.edit().putString(USER_EMAIL,value).apply()

//    val requestQueue = Volley.newRequestQueue(context)

}