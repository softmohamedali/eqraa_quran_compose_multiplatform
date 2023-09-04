package com.moali.eqraa.core.shared.local

import com.moali.eqraa.domain.abstractions.local.DataStoreOper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

actual class DataStoreOperImp: DataStoreOper {
    override suspend fun getSebhaCounter(): Int? {
        return null
    }

    override suspend fun saveSebhaCounter(counterValue: Int) {

    }
}