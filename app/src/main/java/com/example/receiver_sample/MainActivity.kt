package com.example.receiver_sample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val systemReceiver = SystemReceiver()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        registerReceiver(systemReceiver, filter)
    }
}

class SystemReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirplane = intent?.getBooleanExtra(AIRPLANE_STATE, false) ?: false
        if (isAirplane) {
            Toast.makeText(context, "機内モードなう", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Not機内モード", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private const val AIRPLANE_STATE = "state"
    }
}