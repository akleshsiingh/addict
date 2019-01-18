package com.example.healthnode.ui.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        onViewReady(savedInstanceState,intent)
    }

    abstract fun getContentView(): Int

    abstract fun onViewReady(savedInstanceState: Bundle?, intent: Intent?)
}