package com.example.myhandler

open class MyHandler {

    val queue = MyLooper.myQueue()

    val looper = MyLooper.myLooper()

    fun sendMessage(message: MyMessage) {
        enqueueMessage(message)
    }

    private fun enqueueMessage(message: MyMessage) {
        val myQueue = queue
        message.target = this
        myQueue.enqueueMessage(message)
    }

    open fun handleMessage(message: MyMessage) {}


    fun dispatchMessage(message: MyMessage) {
        handleMessage(message)
    }
}