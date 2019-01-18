package com.example.healthnode.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
data class Target(@PrimaryKey(autoGenerate = true) val id: Int=0, val title: String = "", val target: Int = 0) {
    @Ignore
    constructor() : this(0)
}