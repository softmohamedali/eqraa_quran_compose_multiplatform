package com.moali.eqraa.core.shared.services

expect class ServicesUtils{
    fun startServiceIntentToCreatePlayerNotification(
        souraName:String,
        shehkName:String
    )
    fun startServiceIntentToCreateSebhaFloating()

    fun canDrawOverlays():Boolean

    // if you in android for example some sbecifc intent need to run with start activit yso this fun will solve the problem
    suspend fun startMainComponentAction(mainComponentActionId:Int)
}