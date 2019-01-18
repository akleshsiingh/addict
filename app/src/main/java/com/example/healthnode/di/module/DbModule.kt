package com.example.healthnode.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.example.healthnode.data.dao.TargetDao
import com.example.healthnode.data.db.AppDatabase
import com.example.healthnode.utils.Logger
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDb(app: Application): AppDatabase {
        return Room.databaseBuilder(app.applicationContext, AppDatabase::class.java, "h.db").build()
    }

    @Provides
    @Singleton
    fun provideTargetDao(db: AppDatabase): TargetDao {
        return db.getTargetDao()
    }
}