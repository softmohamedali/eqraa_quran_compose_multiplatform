package com.moali.eqraa.core.utils

import androidx.datastore.preferences.core.intPreferencesKey
import com.moali.eqraa.data.datastore.ConstantsDataStore

object DataStorePhrefrencesKeys {
    val sebhaPrefKey= intPreferencesKey(ConstantsDataStore.SEBHA_COUNTER_KEY)
    val currentTasbehaPrefKey= intPreferencesKey(ConstantsDataStore.TASBEHA_KEY)
    val mainComponentActionPrefKey= intPreferencesKey(ConstantsDataStore.MAIN_COMPONENT_ACTION_KEY)
}