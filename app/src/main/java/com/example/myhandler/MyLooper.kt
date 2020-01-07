package com.example.myhandler

import java.lang.RuntimeException

class MyLooper private constructor() {

    companion object {
        private val sThreadLocalLooper: ThreadLocal<MyLooper> = ThreadLocal()

        fun prepare() {
            if (sThreadLocalLooper.get() != null) {
                throw RuntimeException("looper can not prepare twice")
            }
            sThreadLocalLooper.set(MyLooper())
        }

        fun myLooper(): MyLooper {
            val myLooper = sThreadLocalLooper.get()
            requireNotNull(myLooper, { "should call prepare() first" })
            return myLooper
        }

        fun myQueue(): MyMessageQueue {
            val myLooper = sThreadLocalLooper.get()
            requireNotNull(myLooper, { "should call prepare() first" })
            return myLooper.queue
        }

        fun loop() {
            while (true) {
                val message = myQueue().next()
                message.target?.dispatchMessage(message)
            }
        }
    }

    private val queue: MyMessageQueue = MyMessageQueue()


}