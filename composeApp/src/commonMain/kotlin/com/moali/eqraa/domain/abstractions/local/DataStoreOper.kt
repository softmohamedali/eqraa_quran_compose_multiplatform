package com.moali.eqraa.domain.abstractions.local

import kotlinx.coroutines.flow.Flow

interface DataStoreOper {
    suspend fun getSebhaCounter():Int?

    suspend fun getSebhaPrefAsFlow():Flow<Int?>

    suspend fun saveSebhaCounter(counterValue:Int)

    suspend fun getCurrentTasbeha():Flow<Int?>

    suspend fun saveTasbeha(tasbehaId:Int)

    suspend fun sendAction(actionId:Int)
    suspend fun listenMainComponentAction():Flow<Int?>

}