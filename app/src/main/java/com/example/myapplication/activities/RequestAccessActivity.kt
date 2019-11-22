package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.app.SearchManager
import android.content.Context
import android.util.Log
import com.example.myapplication.R


class RequestAccessActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_access)
        searchView = findViewById(R.id.searchView)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.isIconifiedByDefault = true
        searchView.setOnQueryTextListener(textChangeListener)
    }
    private val textChangeListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextChange(cs: String): Boolean {
            Log.d("search", "Query:: $cs")
            return false
        }

        override fun onQueryTextSubmit(query: String): Boolean {
            Log.d("search", "Query:: $query")
            return false
        }
    }
}
