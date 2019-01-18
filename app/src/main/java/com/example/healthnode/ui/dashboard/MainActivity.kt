package com.example.healthnode.ui.dashboard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.healthnode.R
import com.example.healthnode.data.db.AppDatabase
import com.example.healthnode.ui.base.BaseActivity
import com.example.healthnode.utils.Logger
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.fitness.data.BleDevice
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.request.BleScanCallback
import java.util.*
import javax.inject.Inject


private const val TAG = "MAIN_ACTIVITY"

class MainActivity : BaseActivity() {

    @Inject lateinit var db:AppDatabase
    override fun getContentView() = R.layout.activity_main

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent?) {
        btnScan.click {
            startScanning()
        }
    }

    private fun startScanning() {
        GoogleSignIn.getLastSignedInAccount(this)?.also {
            val response = Fitness.getBleClient(
                this, it
            ).startBleScan(Arrays.asList(DataType.TYPE_STEP_COUNT_DELTA), 1000, callback)
        }

    }

    val callback = object : BleScanCallback() {
        override fun onDeviceFound(device: BleDevice) {
            // A device that provides the requested data types is available
            Logger.e(TAG, " FOUND $device")
        }

        override fun onScanStopped() {
            // The scan timed out or was interrupted
            Logger.e(TAG, " SCANNING STOPPED")
        }
    }
}

private fun View.click(function: () -> Unit) {
    this.setOnClickListener { function.invoke() }
}
