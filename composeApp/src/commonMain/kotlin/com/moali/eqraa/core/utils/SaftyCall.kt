package com.moali.eqraa.core.utils

import io.ktor.utils.io.errors.IOException

suspend fun <T>safeCall(callBack:suspend ()->ResultState<T>):ResultState<T>{
    return try {
        callBack()
    }
     catch(e: IOException) {
        return ResultState.IsError("Oops! Something went wrong. Please try again.")

    }
    catch (e:Exception){
        return ResultState.IsError(e.message?:"unkwon Error")
    }
}