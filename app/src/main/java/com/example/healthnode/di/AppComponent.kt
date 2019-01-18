package com.example.healthnode.di

import android.app.Application
import com.example.healthnode.MvvmApplication
import com.example.healthnode.di.module.ActivityBuilderModule
import com.example.healthnode.di.module.AppModule
import com.example.healthnode.di.module.DbModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityBuilderModule::class, AndroidSupportInjectionModule::class,DbModule::class,AppModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindApp(app:Application): AppComponent.Builder
        fun build(): AppComponent
    }

}