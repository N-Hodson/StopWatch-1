package com.example.stopwatch

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    lateinit var start: Button
    lateinit var reset: Button
    lateinit var stopwatch: Chronometer
    var on = false
    var time = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wireWidgets()
        start.setOnClickListener {
            if (!on) {
                stopwatch.base = SystemClock.elapsedRealtime()+time
                stopwatch.start()
                on = true
            }
            else if (on){
                stopwatch.stop()
                on = false
                time = (stopwatch.base - SystemClock.elapsedRealtime())
            }
        }
        reset.setOnClickListener {
            stopwatch.base = SystemClock.elapsedRealtime()
            time = 0
        }
    }

    private fun wireWidgets() {
        start = findViewById(R.id.button_main_start)
        reset = findViewById(R.id.button_main_reset)
        stopwatch = findViewById(R.id.chronometer_main_stopwatch)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(on){
            time = (SystemClock.elapsedRealtime()-stopwatch.base)
        }
        outState.putLong("saveTime", time)
        outState.putBoolean("saveOn", on)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        time = savedInstanceState.getLong("savedTime")
        on = savedInstanceState.getBoolean("saveOn")
        stopwatch.base = SystemClock.elapsedRealtime()-time
        if(on){
            stopwatch.start()
        }
    }

    companion object {
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Log.d(TAG, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
        start = findViewById(R.id.button_main_start)
        reset = findViewById(R.id.button_main_reset)
        stopwatch = findViewById(R.id.chronometer_main_stopwatch)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}