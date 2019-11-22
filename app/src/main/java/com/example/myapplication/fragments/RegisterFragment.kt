package com.example.myapplication.fragments

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.activities.ChooseActivity
import com.example.myapplication.activities.LoginActivity
import com.example.myapplication.activities.MainActivity
import com.example.myapplication.modals.user.InitUser
import com.example.myapplication.utilities.request.Request
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_register.*
import org.json.JSONObject

class RegisterFragment : Fragment() {
    private lateinit var progressbar:ProgressBar
    private lateinit var nameField:EditText
    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var signUpButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        progressbar.visibility = View.INVISIBLE
    }


    private fun initView(view: View){
        progressbar = view.findViewById(R.id.progressBar2)
        nameField = view.findViewById(R.id.nameFieldRes)
        emailField = view.findViewById(R.id.emailFieldRes)
        passwordField = view.findViewById(R.id.passwordFieldRes)
        signUpButton = view.findViewById(R.id.signUpBtn)
        signUpButton.setOnClickListener(registerListener)
    }

    private val registerListener = View.OnClickListener {
        startActivity(Intent(requireContext(),ChooseActivity::class.java))
        if(true){
            val name = nameField.text.toString().trim()
            val username= emailField.text.toString().trim()
            val password= passwordField.text.toString().trim()
            if(name.isEmpty()){
                nameField.error = "name required"
            }else if(username.isEmpty()){
                emailField.error = "username required"
            }else if (password.isEmpty()){
                passwordField.error = "password required"
            }else{
                progressbar.visibility = View.VISIBLE
                val data = JSONObject()
                data.put("name",name)
                data.put("username",username)
                data.put("password",password)
                data.put("device_token","dslwdkjjkdsfjkheoskjsklfjherlskjfkjhsfkksflkhsdklgjheurkfghnjgfkjhdrgrhekfkjff")
                Request(requireContext()).post("/register",data).header("accept","application/json").then{
                    startActivity(Intent(requireContext(), ChooseActivity::class.java))
                    progressbar.visibility = View.INVISIBLE
                    val  registerUser= Gson().fromJson(it, InitUser::class.java)
//                    Toast.makeText(context, registerUser.user.email,Toast.LENGTH_SHORT).show()
                }.catch {
                    progressbar.visibility = View.INVISIBLE
                    Toast.makeText(context, "error ${it.message}",Toast.LENGTH_SHORT).show()

                }
            }
        }
    }




}