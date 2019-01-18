package com.example.healthnode

import android.util.Log

class Logger {

    companion object {
        val isActive: Boolean = true
        fun e(tag: String, value: String) {
            if (isActive)
                Log.e(tag, value)
        }
    }
}