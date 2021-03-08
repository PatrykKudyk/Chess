package com.partos.chess

import android.app.Application

class MyApp: Application() {

    companion object {
        var searchedNodes = HashSet<String>()
    }
}