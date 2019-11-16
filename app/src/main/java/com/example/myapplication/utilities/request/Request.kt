package com.example.myapplication.utilities.request

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request.Method
import org.json.JSONObject


typealias SuccessAck = (body: String) -> Unit

class Request(private val context: Context) {
    private var successCallback: SuccessAck? = null
    private val _headers = HashMap<String, String>()
    private var errorCallback: ((res: Error) -> Unit)? = null

    private fun doRequest(method: Int, url: String, data: JSONObject? = null) {
        val r = object: StringRequest(method, "http://fs.tinycode.in/api$url", Response.Listener {
            successCallback?.invoke(it!!)
        }, Response.ErrorListener {
            val statusCode = it.networkResponse.statusCode
            errorCallback?.invoke(
                Error(it.networkResponse.data.toString(charset("UTF-8")), statusCode)
            )
        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getHeaders(): MutableMap<String, String> {
//                _headers["Authorization"] = ""
                return _headers
            }

            override fun getBody(): ByteArray {
                if (data == null) {
                    return super.getBody()
                }
                return data.toString().toByteArray()
            }
        }

        Volley.newRequestQueue(context).add(r)
    }

    fun header(key: String, value: String): Request {
        _headers[key] = value
        return this
    }

    fun get(url: String): Request {
        doRequest(Method.GET, url)
        return this
    }


    fun post(url: String, data: JSONObject): Request {
        doRequest(Method.POST, url, data)
        return this
    }

    fun post(url: String, data: Map<String, String>): Request {
        var json = JSONObject()
        for ((k, v) in data) {
            json.put(k, v)
        }
        post(url, json)
        return this
    }

    fun put() {

    }

    fun then(callback: SuccessAck): Request {
        successCallback = callback
        return this
    }

    fun catch(callback: (err: Error) -> Unit) {
        errorCallback = callback
    }

    fun delete() {

    }
}