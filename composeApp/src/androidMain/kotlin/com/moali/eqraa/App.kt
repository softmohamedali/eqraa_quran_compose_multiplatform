package com.moali.eqraa

import android.app.Application
import com.moali.eqraa.di.initKoin
import org.koin.android.ext.koin.androidContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@App)
//            modules(viewModelModule)
        }
    }
}
