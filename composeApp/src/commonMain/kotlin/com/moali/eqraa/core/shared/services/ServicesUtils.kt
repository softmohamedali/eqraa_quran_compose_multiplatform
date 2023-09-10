package com.moali.eqraa.core.shared.services

expect class ServicesUtils{
    fun startServiceIntentToCreatePlayerNotification(
        souraName:String,
        shehkName:String
    )
    fun startServiceIntentToCreateSebhaFloating()
}