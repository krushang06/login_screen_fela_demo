package com.example.login_screen_test.utils

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log
import android.widget.Toast

/*
class UpdateService : Service() {
    var mReceiver: BroadcastReceiver? = null
    override fun onCreate() {
        super.onCreate()
        // register receiver that handles screen on and screen off logic
        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        mReceiver = MyReceiver()
        registerReceiver(mReceiver, filter)
    }

    override fun onDestroy() {
        unregisterReceiver(mReceiver)
        Log.i("onDestroy Reciever", "Called")
        super.onDestroy()
    }

    override fun onStart(intent: Intent, startId: Int) {
        val screenOn = intent.getBooleanExtra("screen_state", false)
        if (!screenOn) {
            Log.i("screenON", "Called")
            Toast.makeText(applicationContext, "Awake", Toast.LENGTH_LONG)
                .show()
        } else {
            Log.i("screenOFF", "Called")
            Toast.makeText(
                applicationContext, "Sleep",
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        // TODO Auto-generated method stub
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return START_STICKY
    }
}*/
