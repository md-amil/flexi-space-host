package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.R
import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.fragments.LoginFragment
import com.example.myapplication.fragments.RegisterFragment
import com.example.myapplication.modals.user.InitUser
import com.example.myapplication.utilities.request.Request
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_login.*
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {
    private val tag = "login"
    private lateinit var registerBtn:Button
    private lateinit var loginBtn:Button
    private val registerFragment:Fragment = RegisterFragment()
    private val loginFragment:Fragment = LoginFragment()
    private var activeFragment:Fragment = loginFragment

    private val fragMan:FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        fragMan.beginTransaction().add(R.id.container_login, loginFragment, "1").commit()
        fragMan.beginTransaction().add(R.id.container_login,registerFragment , "2").hide(registerFragment).commit()

    }
    private fun initView(){
        loginBtn = findViewById(R.id.switchLoginBtn)
        registerBtn = findViewById(R.id.switchSignUpbtn)
        loginBtn.setOnClickListener(loginListener)
        registerBtn.setOnClickListener(loginListener)
    }
    private val loginListener = View.OnClickListener {
        if(it == loginBtn){
            fragMan.beginTransaction().hide(activeFragment).show(loginFragment).commit()
            activeFragment= loginFragment
        }else if(it == registerBtn){
            fragMan.beginTransaction().hide(activeFragment).show(registerFragment).commit()
            activeFragment = registerFragment
        }
    }
    private fun login(view:View){
        val username:String = emailField.text.toString().trim()
        val password:String = passwordField.text.toString().trim()
//        val username = "rajnish42413@gmail.com"
//        val password = "123456"
        if(username.isNotEmpty() && password.isNotEmpty()){
            val data = JSONObject()
            data.put("username",username)
            data.put("password",password)
            Request(this).post("/login",data).then {
                val initUser = Gson().fromJson(it, InitUser::class.java)
                Log.d(tag,"inter the success callback")
                    if(initUser == null){
                        //user did not assign
                    }else{
                        Toast.makeText(this, "${initUser.user.email}",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, ChooseActivity::class.java))
                        overridePendingTransition(R.anim.slide_up, R.anim.no_animation)
                        //then redirect to choose page
                    }
            }.catch {
                Log.d(tag,"error found")
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
