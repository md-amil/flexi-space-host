package com.example.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.myapplication.modals.user.InitUser
import com.example.myapplication.utilities.App
import com.example.myapplication.utilities.request.Request
import com.google.gson.Gson
import kotlinx.android.synthetic.*
import org.json.JSONObject

class LoginFragment : Fragment() {
    private lateinit var emailField:EditText
    private lateinit var passwordField:EditText
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar
    val TAG = "loginfragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        progressBar.visibility = View.INVISIBLE
    }

    private fun initView(view: View){
        progressBar = view.findViewById(R.id.progressBar)
        emailField = view.findViewById(R.id.emailField)
        passwordField = view.findViewById(R.id.passwordField)
        loginButton = view.findViewById(R.id.login_button)
        loginButton.setOnClickListener(loginUser)
    }
    private val loginUser = View.OnClickListener {
        val username:String = emailField.text.toString().trim()
        val password:String = passwordField.text.toString().trim()
        if(username.isEmpty()){
            emailField.error = "username required"
        }else if(password.isEmpty()){
            passwordField.error = "password required"
        }else{
            progressBar.visibility = View.VISIBLE
            val data = JSONObject()
            data.put("username",username)
            data.put("password",password)
            Request(requireContext()).post("/login",data).then {
                Log.d(TAG,"inter the success callback")
                progressBar.visibility = View.INVISIBLE
                val initUser = Gson().fromJson(it, InitUser::class.java)
                if(initUser == null){
                    //user did not assign
                }else{
                    App.loginPrefs.authToken = initUser.token
                    App.loginPrefs.isLoggedIn = true
                    Toast.makeText(context, "${initUser.user.email}", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(requireContext(), ChooseActivity::class.java))
                }
            }.catch {
                Log.d(TAG,"inter the error call back callback")
                progressBar.visibility = View.INVISIBLE
                if(it.statusCode == 401){
                    Toast.makeText(context, "page not found", Toast.LENGTH_SHORT).show()
                }else if(it.statusCode == 422){
                    Toast.makeText(context, " 422 found - ${it.message}", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
