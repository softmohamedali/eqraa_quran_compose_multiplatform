//package com.moali.eqraa.core.shared
//
//import app.cash.sqldelight.db.SqlDriver
//import com.moali.eqraa.data.local.SqlDNoteDataSource
//import com.moali.eqraa.database.EqraaDatabase
//
//actual class DataBaseDriverFactory{
//    actual fun create(): SqlDriver {
//        return Any() as SqlDriver
//    }
//}
//
//
//actual class ProvideSqlDelightNoteDataSource(
//){
//    actual val sqlDNoteDataSource: SqlDNoteDataSource by lazy {
//        SqlDNoteDataSource(
//            db = EqraaDatabase(
//                DataBaseDriverFactory().create()
//            )
//        )
//    }
//}