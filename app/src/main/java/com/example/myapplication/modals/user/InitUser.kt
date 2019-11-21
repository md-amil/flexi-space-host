package com.example.myapplication.modals.user

class InitUser(
    val user:User,
    val token:String
) {
    override fun toString(): String {
        return token
    }
}