package com.moali.eqraa.core.shared

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.moali.eqraa.data.local.SqlDNoteDataSource
import com.moali.eqraa.database.EqraaDatabase
import java.io.File

actual class DataBaseDriverFactory{
    actual fun create(): SqlDriver {
        val databasePath = File(System.getProperty("java.io.tmpdir"), "eqraa.db")
//        val driver = JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.path}")
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        if (!File("eqraa.db").exists()) {
            EqraaDatabase.Schema.create(driver)
        }

        return driver
    }
}

actual class ProvideSqlDelightNoteDataSource(
){
    actual val sqlDNoteDataSource: SqlDNoteDataSource by lazy {
        SqlDNoteDataSource(
            db = EqraaDatabase(
                DataBaseDriverFactory().create()
            )
        )
    }

}

