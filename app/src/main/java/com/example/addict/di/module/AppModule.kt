package com.example.addict.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun bindApp(app: Application):Context
}