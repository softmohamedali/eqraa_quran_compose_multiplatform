package com.moali.eqraa.core.shared

import dev.icerock.moko.resources.StringResource


//this class will be used if you try to use moko in specific platform
expect class Strings {
    fun get(id: StringResource, args: List<Any>): String
}