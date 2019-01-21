package com.example.addict

import com.example.addict.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MvvmApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().bindApp(this)
            .build()

        appComponent.inject(this)
        return appComponent
    }
}