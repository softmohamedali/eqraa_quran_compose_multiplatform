package com.moali.eqraa.core.shared

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format

actual class Strings {
    actual fun get(id: StringResource, args: List<Any>): String{
        if (args.isEmpty()){
            return StringDesc.Resource(id).localized()
        }else{
            return id.format(*args.toTypedArray()).localized()
        }
    }
}