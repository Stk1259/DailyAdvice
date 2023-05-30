package com.example.dailyadvice.data

import com.example.dailyadvice.R

object BackgroundDataSource {
    var backgroundList = mutableListOf<BackgroundModel>()
    private var backgroundIndex = 0
    fun shuffleBackground(){
        backgroundList = mutableListOf(
            BackgroundModel(R.drawable.background1),
            BackgroundModel(R.drawable.background2),
            BackgroundModel(R.drawable.background3),
            BackgroundModel(R.drawable.background4)
        )
        backgroundList.shuffle()
        backgroundIndex = 0
    }

    fun getNextBackground(): BackgroundModel{
            backgroundIndex = (backgroundIndex + 1) % backgroundList.size
            if (backgroundIndex == backgroundList.size - 1) {
                shuffleBackground()
            }
            return backgroundList[backgroundIndex]
    }
}