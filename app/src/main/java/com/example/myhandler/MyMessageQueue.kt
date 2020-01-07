package com.example.myhandler

import java.util.concurrent.LinkedBlockingQueue

class MyMessageQueue {

    private val mMessages = LinkedBlockingQueue<MyMessage>()

    fun next(): MyMessage {
        return mMessages.take()
    }

    fun enqueueMessage(message: MyMessage) {
        mMessages.put(message)
    }
}