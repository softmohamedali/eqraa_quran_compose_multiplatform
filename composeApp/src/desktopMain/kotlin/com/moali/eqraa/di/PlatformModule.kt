package com.moali.eqraa.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.moali.eqraa.core.shared.MediaPlayerController
import com.moali.eqraa.data.local.SqlDNoteDataSource
import com.moali.eqraa.database.EqraaDatabase
import com.moali.eqraa.domain.abstractions.MediaPlayerOperation
import com.moali.eqraa.domain.abstractions.NoteDataSource
import org.koin.dsl.module
import java.io.File

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
            val databasePath = File(System.getProperty("java.io.tmpdir"), "eqraa.db")
//        val driver = JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.path}")
            val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
            if (!File("eqraa.db").exists()) {
                EqraaDatabase.Schema.create(driver)
            }
            driver
        }

        single<MediaPlayerOperation> { MediaPlayerController() }
    }