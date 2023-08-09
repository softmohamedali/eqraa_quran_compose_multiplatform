package com.moali.eqraa.core.shared

import com.moali.eqraa.data.local.SqlDNoteDataSource
import com.moali.eqraa.database.EqraaDatabase
import com.squareup.sqldelight.db.SqlDriver

actual class DataBaseDriverFactory{
    actual fun create():SqlDriver{
        return Any() as SqlDriver
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