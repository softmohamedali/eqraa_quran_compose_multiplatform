//package com.moali.MyQuran.core.utils
//
//import kotlinx.datetime.LocalDateTime
//import java.time
//import java.time.format.DateTimeFormatter
//import kotlin.random.Random
//
//object DateTimeFormated {
//
//    fun getTimeYMD(): String {
//        val current = LocalDateTime.now()
//        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//        return current.format(formatter)
//    }
//
//    fun getDay(): String {
//        val current = LocalDateTime.now()
//        val formatter = DateTimeFormatter.ofPattern("dd")
//        return current.format(formatter)
//    }
//
//    fun geMonth(): String {
//        val current = LocalDateTime.now()
//        val formatter = DateTimeFormatter.ofPattern("MM")
//        return current.format(formatter)
//    }
//
//    fun getTimeMD(): String {
//        val current = LocalDateTime.now()
//        val formatter = DateTimeFormatter.ofPattern("MM-dd")
//        return current.format(formatter)
//    }
//
//
//    fun getTimeYMDHMinuit(): String {
//        val current = LocalDateTime.now()
//        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm")
//        return current.format(formatter)
//    }
//
//    fun getCurrentTimeWithHourNormal():String{
//        val current = LocalDateTime.now()
//        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm")
//        val formatted = current.format(formatter)
////        Calendar.getInstance().time.time
//        return formatted + Random.nextInt()
//    }
//
//
//}