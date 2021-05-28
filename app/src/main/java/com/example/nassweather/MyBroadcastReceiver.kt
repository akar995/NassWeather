package com.example.nassweather

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.util.Log
import androidx.work.*

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("Receive", "Receive called")
        val workManager = WorkManager.getInstance(context!!.applicationContext)

        if (intent?.action.equals(Intent.ACTION_POWER_CONNECTED)) {

            val constraints =
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            val uploadWorkRequest: WorkRequest =
                OneTimeWorkRequestBuilder<GetWeatherInBackground>().setConstraints(constraints)
                    .build()
            workManager.enqueue(uploadWorkRequest)

        } else {
            val wifiState =
                intent?.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)
            when (wifiState) {
                WifiManager.WIFI_STATE_ENABLED -> {
                    Log.e("Receive", "network changed")

                    val constraints =
                        Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
                            .setRequiresCharging(true).build()
                    val uploadWorkRequest: WorkRequest =
                        OneTimeWorkRequestBuilder<GetWeatherInBackground>().setConstraints(
                            constraints
                        ) .build()
                    workManager.enqueue(uploadWorkRequest)
                }
            }

        }
    }
}