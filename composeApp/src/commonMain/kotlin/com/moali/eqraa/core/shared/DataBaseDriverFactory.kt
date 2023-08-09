package com.moali.eqraa.core.shared

import com.moali.eqraa.data.local.SqlDNoteDataSource
import com.squareup.sqldelight.db.SqlDriver


expect class DataBaseDriverFactory{
    fun create(): SqlDriver
}



expect class ProvideSqlDelightNoteDataSource{
    val sqlDNoteDataSource:SqlDNoteDataSource
}


