package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.myapplication.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.fragments.LoginFragment
import com.example.myapplication.fragments.RegisterFragment

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
}









