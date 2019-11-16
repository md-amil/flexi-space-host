package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.myapplication.R
import com.example.myapplication.utilities.request.Request
import org.json.JSONObject

class AddWorkspaceActivity : AppCompatActivity() {
    private val tag = "add_work_space_activity"
    private lateinit var nameField: EditText
    private lateinit var websiteField: EditText
    private lateinit var addressField: EditText
    private lateinit var contactField: EditText
    private lateinit var proceedBtn: Button
    private val authToken =
        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjFmYWE1M2Y2YmY2MDQ5MzE4MTRjNTBlN2Q3YTUxMmE5Mzc1MzczNGJlY2U4ZmE5ZDExMWYyY2RhYmYyY2RjMTY0YzMwYTc3OGE0Mzg5Mzg2In0.eyJhdWQiOiIxIiwianRpIjoiMWZhYTUzZjZiZjYwNDkzMTgxNGM1MGU3ZDdhNTEyYTkzNzUzNzM0YmVjZThmYTlkMTExZjJjZGFiZjJjZGMxNjRjMzBhNzc4YTQzODkzODYiLCJpYXQiOjE1NzM4MDczMjksIm5iZiI6MTU3MzgwNzMyOSwiZXhwIjoxNjA1NDI5NzI5LCJzdWIiOiI5MSIsInNjb3BlcyI6W119.iKJgBiMNhJ-KFphz9LdtS3L8pJe4uCn9RzM6Mx852ZL4cOmB2o3fMf2lY1z4I0tDS8AoaBTl95g_-uWQKESuBjHvBpk4B0hMWaKpcUxUftUnoK_OFGap_innwcsP1ppDueAoEMAzjkto_plb-rdVIzjygC_ZqzxsxB4SebwUxISu91rWNlkU1Ov381CB2G5vtTbxOA7cvWhL2gTBixAwOgzEvmGNnL-jO89ZCfAkjVzxJfiFBL3tP6_gx1fxsmOfTnfA6mWF2tGcCXoTNP7WVxdv0RrFCJv-X-4PH3RCBkdQ_uGC9XTBLJ9vEmo6aU-FrE-iwb03Ia_4zkKYy7stUK3B61it4L9ELNhNU5yuHY6G8VzpH-X1kG0HnzxDQrwDt58QXw5LPbK6FMPGlgtncpY_4mWQXuJXsWdF1XExrc1QB0RjBv0Z848WUsAo7JKkIIVnqjdyxnM2Hsz8G48dhcrbH-jyymtFbwFFL1-v03WqEkZHjjVgcteahAQNb-MDmH5xdRnTMi9b9ITe_kEMH0MIUkqnt-5QfruUGA-ZMwwP1WowfKa3FejcqS03yM6C9tZe-tzZmjZJ8zgLSwYR7FChjnnoldIakChmHDKAMDhWsyS0I5T0nYdMEDUqw2X8fjiPxzaYCkKwaJe3bVh4pESIQGt8fMj_JjbdkrJpLn4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_workspace)
        initView()
    }

    private fun initView() {
        nameField = findViewById(R.id.nameField)
        websiteField = findViewById(R.id.websiteField)
        addressField = findViewById(R.id.addressField)
        contactField = findViewById(R.id.contactField)
        proceedBtn = findViewById(R.id.proceedBtn)
        proceedBtn.setOnClickListener {
            proceed(it)
        }
    }

    private fun proceed(view: View) {
        val name = nameField.text.toString().trim()
        val website = websiteField.text.toString().trim()
        val address = addressField.text.toString().trim()
        val contactNo = contactField.text.toString().trim()

        if (name.isNotEmpty()) {
            val data = JSONObject()
            data.put("name", name)
            data.put("website", website)
            data.put("address", address)
            data.put("contact", contactNo)
            data.put("capacity",4)

            Request(this).post("/host/parents", data).then {
//                val parentSpace = Gson().fromJson(it, Space::class.java)
                val intent = Intent(this, SpaceCreatedActivity::class.java)
                startActivity(intent)
            }.header("authorization", authToken).header("accept","application/json")
            .catch {
//                if(space added with same name){
//                    nameField.error = it.message
//                }
            }
        }else{
            nameField.error = "please insert name"
        }
    }



}






