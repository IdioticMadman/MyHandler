package com.example.myhandler

data class MyMessage @JvmOverloads constructor(
    var what: Int = 0,
    var obj: Any? = null,
    var target: MyHandler? = null
) {

}