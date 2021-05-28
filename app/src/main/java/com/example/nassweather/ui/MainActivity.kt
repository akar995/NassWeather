package com.example.nassweather.ui

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.Configuration
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.nassweather.MyBroadcastReceiver
import com.example.nassweather.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val br: BroadcastReceiver = MyBroadcastReceiver()
        val filter = IntentFilter(Intent.ACTION_POWER_CONNECTED).apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
        }

        val filter2 = IntentFilter(WifiManager.EXTRA_WIFI_STATE).apply {
            addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        }
        registerReceiver(br, filter)
        registerReceiver(br, filter2)
    }
}