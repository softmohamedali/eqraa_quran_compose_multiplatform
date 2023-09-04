package com.moali.eqraa.domain.abstractions.local

import kotlinx.coroutines.flow.Flow

interface DataStoreOper {
    suspend fun getSebhaCounter():Int?

    suspend fun saveSebhaCounter(counterValue:Int)
}