package com.moali.eqraa.core.shared.local

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.moali.eqraa.core.utils.DataStorePhrefrencesKeys
import com.moali.eqraa.core.utils.log
import com.moali.eqraa.data.datastore.ConstantsDataStore
import com.moali.eqraa.domain.abstractions.local.DataStoreOper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

val Context.dataStore: DataStore<Preferences> by
preferencesDataStore(name =ConstantsDataStore.SEBHA_DATA_STORE_NAME)
actual class DataStoreOperImp(
    private val context: Context
): DataStoreOper {
    override suspend fun getSebhaCounter(): Int? {
        val pref=context.dataStore.data.first()
        return pref[DataStorePhrefrencesKeys.sebhaPrefKey]
    }

    override suspend fun getSebhaPrefAsFlow(): Flow<Int?> {
        return flow {
            context.dataStore.data.collect{
                emit(it[DataStorePhrefrencesKeys.sebhaPrefKey])
            }
        }

    }


    override suspend fun saveSebhaCounter(counterValue: Int) {
        context.dataStore.edit {
            log(counterValue.toString(),"viewmodel datastoreandroid")
            it[DataStorePhrefrencesKeys.sebhaPrefKey]=counterValue
        }
    }
}