package com.example.myapplication

import android.view.View

interface ItemClickListener {
    fun skipButtonClicked(id:Int,view: View)
    fun declineButtonClicked(id:Int,view: View)
    fun acceptButtonClicked(id:Int,view: View)
}