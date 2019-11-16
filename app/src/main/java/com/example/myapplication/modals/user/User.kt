package com.example.myapplication.modals.user

class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val status: Int,
    val type: String,
    val created_at: String,
    val updated_at: String,
    val organization: Int
){
    override fun toString(): String {
        return name
    }
}


