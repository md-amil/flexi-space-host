package com.example.myapplication.utilities.request

import org.json.JSONException
import org.json.JSONObject

class Error(public val response: String, val statusCode: Int) {
    var message: String = ""
    var status: String = "fail"
    init {
        try {
            val json = JSONObject(response)
            if (json.has("message")) {
                message = json.getString("message")
            }
            if (json.has("status")) {
                status = json.getString("status")
            }
        } catch (e: JSONException) {
            message = e.message ?: response
        }
    }
}