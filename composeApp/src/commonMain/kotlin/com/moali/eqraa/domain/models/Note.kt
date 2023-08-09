package com.moali.eqraa.domain.models

data class Note(
    val id :Long?,
    val title :String="",
    val content :String="",
    val createAt :Int=0,
    val priority :String=PriorityType.LOW,
    val rememberMe:String?=null,
    val image:String?=null
) {
}

