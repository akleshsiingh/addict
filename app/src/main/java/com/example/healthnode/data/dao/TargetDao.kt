package com.example.healthnode.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.healthnode.data.entity.Target
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class TargetDao {

    @Query("SELECT * FROM Target")
    abstract fun selectAll(): Flowable<List<Target>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(it: MutableList<Target>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateTarget(target: Target): Long

    @Query("UPDATE Target set current = 0 ")
    abstract fun clearCurrentCounter()
}