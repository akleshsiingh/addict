package com.example.addict.di.module

import com.example.addict.di.ActivityScope
import com.example.addict.ui.dashboard.MainActivity
import com.example.addict.ui.target.TargetActivity
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