package com.example.healthnode.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.healthnode.R
import com.example.healthnode.data.db.AppDatabase
import com.example.healthnode.ui.base.BaseActivity
import com.example.healthnode.utils.click
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


private const val TAG = "MAIN_ACTIVITY"

class MainActivity : BaseActivity() {

    @Inject lateinit var db:AppDatabase
    override fun getContentView() = R.layout.activity_main

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent?) {
        btnScan.click {

        }
    }

}


