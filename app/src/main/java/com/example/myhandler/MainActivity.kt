package com.example.myhandler

import android.app.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private var handler: MyHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        thread {
            MyLooper.prepare()

            handler = object : MyHandler() {
                override fun handleMessage(message: MyMessage) {
                    Log.e(TAG, message.toString())
                }
            }

            handler?.sendMessage(MyMessage(obj = "hello"))

            MyLooper.loop()

        }

        Handler().postDelayed({
            var index = 0
            while (true) {
                handler?.sendMessage(MyMessage(obj = "$index +index"))
                index++
                if (index > 1000) {
                    break
                }
            }
        }, 600)
    }
}
