package com.example.myapplication.modals.user

class InitUser(
    val user:User,
    private val token:String
) {
    override fun toString(): String {
        return token
    }
}