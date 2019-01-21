package com.example.addict.ui.base

import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        onViewReady(savedInstanceState,intent)
    }

    abstract fun getContentView(): Int

    abstract fun onViewReady(savedInstanceState: Bundle?, intent: Intent?)
}