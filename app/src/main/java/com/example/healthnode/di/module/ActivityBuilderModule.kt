package com.example.healthnode.di.module

import com.example.healthnode.di.ActivityScope
import com.example.healthnode.ui.dashboard.MainActivity
import com.example.healthnode.ui.target.TargetActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun contributeMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun contributeTargetActivity(): TargetActivity
}