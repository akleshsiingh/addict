package com.example.healthnode.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.healthnode.data.dao.TargetDao
import com.example.healthnode.data.entity.Target

@Database(entities = [Target::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getTargetDao(): TargetDao
}