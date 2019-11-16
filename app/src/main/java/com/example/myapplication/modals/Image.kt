package com.example.myapplication.modals

class Image(
    val id:Int,
    val entity_id:Int,
    val thumb:String,
    val small:String,
    val medium:String,
    val large:String,
    val original:String,
    val caption:String?,
    val default:Int,
    val Status:Int,
    val created_at:String
) {
    override fun toString(): String {
        return thumb
    }
}