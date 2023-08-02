package com.moali.eqraa.domain.models

data class Note(
    val id :Int=-1,
    val title :String="",
    val content :String="",
    val createAt :Int=0,
    val priority :Priority=Priority.NONE,
    val rememberMe:String?=null,
    val image:String?=null
) {
}