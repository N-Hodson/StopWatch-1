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
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    lateinit var start: Button
    lateinit var reset: Button
    lateinit var stopwatch: Chronometer
    var on = false
    var time = 0L

    private fun wireWidgets() {
        start = findViewById(R.id.button_main_start)
        reset = findViewById(R.id.button_main_reset)
        stopwatch = findViewById(R.id.chronometer_main_stopwatch)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wireWidgets()
        start.setOnClickListener {
            if (!on) {
                stopwatch.base = abs(SystemClock.elapsedRealtime()-time)
                stopwatch.start()
                on = true
                start.text="Stop"
            }
            else if(on){
                stopwatch.stop()
                on = false
                time = abs ( SystemClock.elapsedRealtime()-stopwatch.base)
                start.text="Start"
            }
        }
        reset.setOnClickListener {
            stopwatch.base = abs(SystemClock.elapsedRealtime())
            time = 0
        }
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(on){
            time = abs(SystemClock.elapsedRealtime()-stopwatch.base)
        }
        outState.putLong("saveTime", time)
        outState.putBoolean("saveOn", on)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        time = savedInstanceState.getLong("saveTime")
        on = savedInstanceState.getBoolean("saveOn")
        stopwatch.base = abs(SystemClock.elapsedRealtime()-time)
        if(on){
            stopwatch.start()
            start.text="Stop"
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