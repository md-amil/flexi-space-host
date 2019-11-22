package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SearchView
import com.example.myapplication.R

class ChooseActivity : AppCompatActivity() {
    private lateinit var addWorkspace :Button
    private lateinit var requestButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)
        addWorkspace = findViewById(R.id.addWorkspaceBtn)
        requestButton= findViewById(R.id.requestAccessBtn)
        addWorkspace.setOnClickListener(listener)
        requestButton.setOnClickListener(listener)
    }
    private val listener = View.OnClickListener {
        when(it.id){
            addWorkspace.id ->{
                val intent =  Intent(this,AddWorkspaceActivity::class.java)
                startActivity(intent)
            }
            requestButton.id->{
                val intent =  Intent(this,RequestAccessActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
