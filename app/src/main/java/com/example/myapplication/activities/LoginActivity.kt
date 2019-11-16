package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.R
import android.content.Intent
import com.example.myapplication.modals.user.InitUser
import com.example.myapplication.modals.user.User
import com.example.myapplication.utilities.request.Request
import com.google.gson.Gson
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {
    private val tag = "login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            login(it)
        }
    }

    private fun login(view:View){
//        val email:String = emailField.text.toString().trim()
//        val password:String = passwordField.text.toString().trim()
        val username = "rajnish42413@gmail.com"
        val password = "123456"
        if(username.isNotEmpty() && password.isNotEmpty()){
            val data = JSONObject()
            data.put("username",username)
            data.put("password",password)
            Request(this).post("/login",data).then {
                val initUser = Gson().fromJson(it, InitUser::class.java)
                    if(initUser == null){
                        //user did not assign
                    }else{
                        Toast.makeText(this, "${initUser.user.email}",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, ChooseActivity::class.java))
                        overridePendingTransition(R.anim.slide_up, R.anim.no_animation)
                        //then redirect to choose page
                    }
            }.catch {
                if(it.statusCode == 401){
                    Toast.makeText(this, "page not found",Toast.LENGTH_SHORT).show()
                }else if(it.statusCode == 422){
                    Toast.makeText(this, " 422 found - ${it.message}",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, it.message,Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(this,"please make sure that email and password are filled in",
                Toast.LENGTH_SHORT).show()
        }






    }









}
