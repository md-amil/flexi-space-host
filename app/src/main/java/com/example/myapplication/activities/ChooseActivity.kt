package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R

class ChooseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)
        val addWorkspace = findViewById<Button>(R.id.addWorkspaceBtn)
        val requestbutton= findViewById<Button>(R.id.addWorkspaceBtn)
        addWorkspace.setOnClickListener {
           val intent =  Intent(this,AddWorkspaceActivity::class.java)
            startActivity(intent)
        }
    }
}
