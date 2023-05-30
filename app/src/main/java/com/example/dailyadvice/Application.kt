package com.example.dailyadvice

import android.app.Application
import android.util.Log
import com.example.dailyadvice.data.AdviceDataSource
import com.example.dailyadvice.data.BackgroundDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MyApplication: Application() {

    override fun onCreate(){
        super.onCreate()
    }
}