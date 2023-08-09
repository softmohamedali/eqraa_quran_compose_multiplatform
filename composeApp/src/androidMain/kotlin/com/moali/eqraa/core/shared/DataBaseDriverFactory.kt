package com.moali.eqraa.core.shared

import android.content.Context
import com.moali.eqraa.data.local.SqlDNoteDataSource
import com.moali.eqraa.database.EqraaDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.android.AndroidSqliteDriver

actual class DataBaseDriverFactory(
    private val context: Context
){
    actual fun create():SqlDriver{
        return AndroidSqliteDriver(
            EqraaDatabase.Schema,
            context,
            "eqraa.db"
        )
    }
}




actual class ProvideSqlDelightNoteDataSource(
    private val context: Context
){
    actual val sqlDNoteDataSource:SqlDNoteDataSource by lazy {
        SqlDNoteDataSource(
            db = EqraaDatabase(
                DataBaseDriverFactory(context).create()
            )
        )
    }
}

