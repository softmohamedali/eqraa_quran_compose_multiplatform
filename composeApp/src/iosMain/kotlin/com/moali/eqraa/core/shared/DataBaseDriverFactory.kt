package com.moali.eqraa.core.shared

import com.moali.eqraa.data.local.SqlDNoteDataSource
import com.moali.eqraa.database.EqraaDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DataBaseDriverFactory{
    actual fun create():SqlDriver{
        return NativeSqliteDriver(
            EqraaDatabase.Schema,
            "eqraa.db"
        )
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