package com.moali.eqraa.core.utils

import co.touchlab.kermit.Logger


fun log(text:String,tag:String="uarf"){
    Logger.i(tag=tag) {
        text
    }
}