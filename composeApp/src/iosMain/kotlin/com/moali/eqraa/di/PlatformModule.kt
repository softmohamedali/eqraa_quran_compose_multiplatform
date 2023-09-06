package com.moali.eqraa.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.moali.eqraa.core.shared.utils.MediaPlayerController
import com.moali.eqraa.data.local.SqlDNoteDataSource
import com.moali.eqraa.database.EqraaDatabase
import com.moali.eqraa.domain.abstractions.media.MediaPlayerOperation
import com.moali.eqraa.domain.abstractions.local.NoteDataSource
import org.koin.dsl.module

actual fun platformModule()=
    module {
        single<NoteDataSource> {
            SqlDNoteDataSource(
                db =  EqraaDatabase(
                    get()
                ),
                get()
            )
        }

        single<SqlDriver> {
            NativeSqliteDriver(
                EqraaDatabase.Schema,
                "eqraa.db"
            )
        }

        single<MediaPlayerOperation> { MediaPlayerController() }
    }


