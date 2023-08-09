package com.moali.eqraa.core.shared

import android.content.Context
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format

actual class Strings(
    val context: Context
) {
    actual fun get(id: StringResource, args: List<Any>): String{
        if (args.isEmpty()){
            return StringDesc.Resource(id).toString(context)
        }else{
            return id.format(*args.toTypedArray()).toString(context)
        }
    }
}