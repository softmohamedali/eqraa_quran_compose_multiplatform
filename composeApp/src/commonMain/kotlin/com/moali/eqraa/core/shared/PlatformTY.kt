package com.moali.eqraa.core.shared

import com.moali.eqraa.core.utils.PlatformType

expect object PlatformTY {
    fun getPlatform(): PlatformType
}