package com.moali.eqraa.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.moali.eqraa.core.shared.utils.MediaPlayerController
import com.moali.eqraa.core.shared.services.ServicesUtils
import com.moali.eqraa.core.shared.local.DataStoreOperImp
import com.moali.eqraa.data.local.SqlDNoteDataSource
import com.moali.eqraa.database.EqraaDatabase
import com.moali.eqraa.domain.abstractions.local.DataStoreOper
import com.moali.eqraa.domain.abstractions.media.MediaPlayerOperation
import com.moali.eqraa.domain.abstractions.local.NoteDataSource
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun platformModule() =
    module {

        single<NoteDataSource> {
            SqlDNoteDataSource(
                db = EqraaDatabase(
                    get()
                ),
                get()
            )
        }

        single<SqlDriver> {
            AndroidSqliteDriver(
                EqraaDatabase.Schema,
                androidContext(),
                "eqraa.db"
            )
        }

        single<MediaPlayerOperation> { MediaPlayerController() }

        single { ServicesUtils(get()) }



        single<DataStoreOper> { DataStoreOperImp(get()) }

        //Data Store settings
        single<SharedPreferences>{ get<Context>().getSharedPreferences("MyShared",Context.MODE_PRIVATE) }
        single<Settings> { SharedPreferencesSettings(get()) }
    }
