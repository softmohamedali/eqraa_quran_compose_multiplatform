package com.moali.eqraa.core.shared.local

import com.moali.eqraa.core.utils.log
import com.moali.eqraa.domain.abstractions.local.DataStoreOper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

actual class DataStoreOperImp: DataStoreOper {
    override suspend fun getSebhaCounter(): Int? {
       return null
    }

    override suspend fun getSebhaPrefAsFlow(): Flow<Int?> {
        return flow {

        }

    }


    override suspend fun saveSebhaCounter(counterValue: Int) {

    }

    override suspend fun getCurrentTasbeha(): Flow<Int?> {
        TODO("Not yet implemented")
    }

    override suspend fun saveTasbeha(tasbehaId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun sendAction(actionId: Int?) {
        TODO("Not yet implemented")
    }

    override suspend fun listenMainComponentAction(): Flow<Int?> {
        TODO("Not yet implemented")
    }
}